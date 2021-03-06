package com.mediatek.ims

import android.annotation.SuppressLint
import android.hardware.radio.V1_0.Dial
import android.hardware.radio.V1_0.RadioResponseInfo
import android.os.RemoteException
import android.telephony.PhoneNumberUtils
import android.telephony.Rlog
import android.telephony.ims.ImsCallProfile
import android.telephony.ims.ImsCallSessionListener
import android.telephony.ims.ImsReasonInfo
import android.telephony.ims.ImsStreamMediaProfile
import android.telephony.ims.stub.ImsCallSessionImplBase
import android.util.Log
import java.util.concurrent.ConcurrentHashMap

class MtImsCallSession

/* For outgoing (MO) calls */(private val mSlotId: Int, profile: ImsCallProfile) : ImsCallSessionImplBase() {
    private val mProfile: ImsCallProfile
    private val mLocalProfile: ImsCallProfile
    private val mRemoteProfile: ImsCallProfile
    private var listener: ImsCallSessionListener? = null

    private var mIndex: Int = -1
    var mCallee: String = ""

    private var mInCall = false

    private val mCallIdLock = Object()
    private var confInProgress = false
    private var mState: Int = 0

    private val mCount: Int

    init {
        this.mCount = sCount++
        this.mProfile = ImsCallProfile(ImsCallProfile.SERVICE_TYPE_NORMAL, profile.callType)
        this.mLocalProfile = ImsCallProfile(ImsCallProfile.SERVICE_TYPE_NORMAL, profile.callType)
        this.mRemoteProfile = ImsCallProfile(ImsCallProfile.SERVICE_TYPE_NORMAL, profile.callType)
        this.mState = State.IDLE
    }

    // For incoming (MT) calls
    constructor(slotId: Int, profile: ImsCallProfile, index: Int, number: String) : this(slotId, profile) {
        updateCall(index, number)
        mState = State.ESTABLISHED
        calls[index] = this
    }

    fun notifyAlerting() {
        mState = State.NEGOTIATING
        listener?.callSessionProgressing(ImsStreamMediaProfile())
    }

    fun notifyHolding() {
        listener?.callSessionHoldReceived(mProfile)
    }


    fun addIdFromRIL(index: Int, number: String) {
        synchronized(sCallsLock) {
            var worked = awaitingIdFromRIL.remove("+$number", this)
            if (!worked)
                worked = awaitingIdFromRIL.remove(number, this)
            if (worked) {
                synchronized(mCallIdLock) {
                    updateCall(index, number)
                    calls[index] = this
                    mCallIdLock.notify()
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun updateCall(nIndex: Int, nNumber: String) {
        val subId = MtImsService.instance!!.subscriptionManager
            .getActiveSubscriptionInfoForSimSlotIndex(mSlotId).subscriptionId

        val telephonyManager = MtImsService.instance!!.telephonyManager.createForSubscriptionId(subId)

        // We have to do lots of complicated formatting stuff here because RIL returns different formats depending on the MCC-MNC
        Log.d(tag, "CC ${telephonyManager.networkCountryIso.toUpperCase()}")
        mProfile.setCallExtra(
            ImsCallProfile.EXTRA_OI, PhoneNumberUtils.formatNumberToE164(
                nNumber,
                (telephonyManager.networkCountryIso
                    ?: telephonyManager.simCountryIso).toUpperCase()
            )
        )

        Log.d(tag, "Using OI ${Rlog.pii(tag, mProfile.getCallExtra(ImsCallProfile.EXTRA_OI))} for profile")

        //mProfile.setCallExtraInt(ImsCallProfile.EXTRA_OIR, call.numberPresentation)
        /*mProfile.setCallExtra(
            ImsCallProfile.EXTRA_CNA,
            if (call.name.isEmpty()) mProfile.getCallExtra(ImsCallProfile.EXTRA_OI) else call.name
        )*/
        //mProfile.setCallExtraInt(ImsCallProfile.EXTRA_CNAP, call.namePresentation)
        mIndex = nIndex
        mCallee = nNumber
    }

    private fun die(reason: ImsReasonInfo) {
        if (mIndex != -1)
            calls.remove(mIndex)
        awaitingIdFromRIL.remove(mCallee)
        mState = State.TERMINATED
        mInCall = false
        listener?.callSessionTerminated(reason)
    }

    fun notifyEnded() {
        die(ImsReasonInfo())
    }

    override fun setListener(listener: ImsCallSessionListener?) {
        this.listener = listener
    }

    override fun getCallId(): String {
        return "slot" + mSlotId + "id" + if (mIndex != -1) "unknown!" + Integer.toString(mCount) else mIndex
    }

    override fun getCallProfile(): ImsCallProfile {
        return mProfile
    }

    override fun getRemoteCallProfile(): ImsCallProfile {
        //return mRemoteProfile;
        return mProfile
    }

    override fun getLocalCallProfile(): ImsCallProfile {
        //return mLocalProfile;
        return mProfile
    }

    override fun getProperty(name: String?): String {
        return mProfile.getCallExtra(name)
    }

    override fun getState(): Int {
        return mState
    }

    override fun isInCall(): Boolean {
        return mInCall
    }

    fun notifyConfDone(index: Int, number: String) {
        listener?.callSessionMergeComplete(MtImsCallSession(mSlotId, mProfile, index, number))
    }

    override fun accept(callType: Int, profile: ImsStreamMediaProfile) {
        mState = State.ESTABLISHING
        RilHolder.getRadio(mSlotId).acceptCall(RilHolder.callback({ radioResponseInfo: RadioResponseInfo, args ->
            if (radioResponseInfo.error != 0) {
                listener?.callSessionInitiatedFailed(
                    ImsReasonInfo(
                        ImsReasonInfo.CODE_OEM_CAUSE_1, radioResponseInfo.error
                    )
                )
                Rlog.e(tag, "Failed to accept IMS call! $radioResponseInfo, $args")
            } else {
                Rlog.d(tag, "Accepted IMS call!")
            }
        }, mSlotId))
    }

    override fun reject(reason: Int) {
        mState = State.TERMINATING
        RilHolder.getRadio(mSlotId).rejectCall(RilHolder.callback({ radioResponseInfo, args ->
            if (radioResponseInfo.error != 0) {
                Rlog.e(tag, "Failed to reject call, $radioResponseInfo, $args")
                mState = State.ESTABLISHED
                listener?.callSessionResumed(mProfile)
            } else
                Rlog.d(tag, "Rejected call")
        }, mSlotId))
    }

    override fun setMute(muted: Boolean) {
        val serial = RilHolder.prepareBlock(mSlotId)
        RilHolder.getRadio(mSlotId).setMute(serial, muted)
        val radioResponseInfo = RilHolder.blockUntilComplete(serial)
        if (radioResponseInfo.error != 0) {
            throw RuntimeException("Failed to setMute $radioResponseInfo")
        }
    }

    override fun start(callee: String, profile: ImsCallProfile?) {
        Log.d(tag, "Calling " + Rlog.pii(tag, callee))
        mCallee = callee
        val callInfo = Dial()
        callInfo.address = callee
        callInfo.clir = profile!!.getCallExtraInt(ImsCallProfile.EXTRA_OIR)
        val extras = profile.mCallExtras.getBundle("OemCallExtras")
        if (extras != null) {
            Rlog.e(tag, "NI reading oemcallextras, it is $extras")
        }

        try {
            Rlog.d(tag, "Adding to awaiting id from ril")
            awaitingIdFromRIL[mCallee] =
                this // Do it sooner rather than later so that this call is not seen as a phantom
            RilHolder.getRadio(mSlotId).dial(RilHolder.callback({ radioResponseInfo, _ ->
                if (radioResponseInfo.error == 0) {
                    Rlog.d(tag, "successfully placed call")
                    /*
                    mInCall = true
                    mState = State.ESTABLISHED
                    listener?.callSessionInitiated(profile)
                    It's done by updateState hopefully
                    */
                } else {
                    Rlog.e(tag, "Call failed")
                    mState = State.TERMINATED
                    awaitingIdFromRIL.remove(callee, this)
                    listener?.callSessionInitiatedFailed(ImsReasonInfo())
                }
            }, mSlotId), callInfo)
        } catch (e: RemoteException) {
            listener?.callSessionInitiatedFailed(ImsReasonInfo())
            awaitingIdFromRIL.remove(callee, this)
            Rlog.e(tag, "Sending dial failed with exception", e)
        }

    }

    override fun terminate(reason: Int) {
        mState = State.TERMINATING
        Rlog.d(tag, "Terminating call")
        RilHolder.getRadio(mSlotId)
            .hangup(RilHolder.callback({ radioResponseInfo: RadioResponseInfo, data: Array<out Any?> ->
                if (radioResponseInfo.error != 0) {
                    Rlog.e(tag, "Failed to terminate call! $radioResponseInfo $data")
                    listener?.callSessionResumed(mProfile)
                }
                // else, it will be handled by updateCall
            }, mSlotId), mIndex)
    }

    override fun startConference(participants: Array<out String>, profile: ImsCallProfile) {
        start(participants[0], profile)
        //TODO is this right?
    }

    override fun deflect(destination: String?) {
        // MTK don't even implement this.
    }

    override fun hold(profile: ImsStreamMediaProfile?) {
        RilHolder.getRadio(mSlotId)
            .holdCall(RilHolder.callback({ radioResponseInfo: RadioResponseInfo, data: Array<out Any?> ->
                if (radioResponseInfo.error != 0) {
                    Rlog.e(tag, "Failed to hold call! $radioResponseInfo $data")
                    listener?.callSessionHoldFailed(ImsReasonInfo())
                }
                // Else, it will be handled by updateCall
            }, mSlotId), mIndex)
    }


    override fun resume(profile: ImsStreamMediaProfile?) {
        RilHolder.getRadio(mSlotId)
            .resumeCall(RilHolder.callback({ radioResponseInfo: RadioResponseInfo, data: Array<out Any?> ->
                if (radioResponseInfo.error != 0) {
                    Rlog.e(tag, "Failed to resume call! $radioResponseInfo $data")
                    listener?.callSessionResumeFailed(ImsReasonInfo())
                }
                // Else, it will be handled by updateCall
            }, mSlotId), mIndex)
    }

    companion object {
        const val tag = "MtImsCallSession"

        val awaitingIdFromRIL = ConcurrentHashMap<String, MtImsCallSession>()
        val calls = ConcurrentHashMap<Int, MtImsCallSession>()

        private var sCount = 0

        val sCallsLock = Object()
    }
}