package com.mediatek.ims

import android.annotation.SuppressLint
import android.hardware.radio.V1_0.Call
import android.hardware.radio.V1_0.RadioResponseInfo
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

/* For outgoing (MO) calls */(val mSlotId: Int, profile: ImsCallProfile) : ImsCallSessionImplBase() {
    private val mProfile: ImsCallProfile
    private val mLocalProfile: ImsCallProfile
    private val mRemoteProfile: ImsCallProfile
    private var listener: ImsCallSessionListener? = null

    var rilImsCall: Call? = null
    private var mInCall = false

    private val mCallIdLock = Object()
    private var confInProgress = false
    private var mState: Int = 0

    var mCallee: String = ""
    private val mCount: Int

    init {
        this.mCount = sCount++
        this.mProfile = ImsCallProfile(ImsCallProfile.SERVICE_TYPE_NORMAL, profile.callType)
        this.mLocalProfile = ImsCallProfile(ImsCallProfile.SERVICE_TYPE_NORMAL, profile.callType)
        this.mRemoteProfile = ImsCallProfile(ImsCallProfile.SERVICE_TYPE_NORMAL, profile.callType)
        this.mState = State.IDLE
    }

    // For incoming (MT) calls
    constructor(slotId: Int, profile: ImsCallProfile, call: Call) : this(slotId, profile) {
        updateCall(call)
        calls[call.index] = this
    }

    fun addIdFromRIL(call: Call) {
        synchronized(sCallsLock) {
            var worked = awaitingIdFromRIL.remove("+" + call.number, this)
            if (!worked)
                worked = awaitingIdFromRIL.remove(call.number, this)
            if (worked) {
                synchronized(mCallIdLock) {
                    updateCall(call)
                    calls[call.index] = this
                    mCallIdLock.notify()
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun updateCall(call: Call) {
        val lastState = mState
        when (call.state) {
            0 // ACTIVE
            -> if (rilImsCall == null) {
                mState = State.ESTABLISHED
                if (listener != null)
                    listener!!.callSessionInitiated(mProfile)
            } else if (rilImsCall!!.state == 2 || // DIALING

                rilImsCall!!.state == 3 || // ALERTING

                rilImsCall!!.state == 4 || // INCOMING

                rilImsCall!!.state == 5
            ) { // WAITING
                mState = State.ESTABLISHED
                if (listener != null)
                    listener!!.callSessionInitiated(mProfile)
            } else if (rilImsCall!!.state == 1 /* HOLDING */ && !confInProgress) { // HOLDING
                if (listener != null)
                    listener!!.callSessionResumed(mProfile)
            } else {
                Rlog.e(tag, "stuff")
            }
            1 // HOLDING
            -> if (listener != null)
                listener!!.callSessionHeld(mProfile)
            2 // DIALING
            -> if (listener != null)
                listener!!.callSessionProgressing(ImsStreamMediaProfile())
            3 // ALERTING
            -> {
                mState = State.NEGOTIATING
                if (rilImsCall == null) {
                    Rlog.e(tag, "Alerting an incoming call wtf?")
                }
                if (listener != null)
                    listener!!.callSessionProgressing(ImsStreamMediaProfile())
            }
            4 // INCOMING
                , 5 // WAITING
            -> {
            }
            6 // END
            -> {
                mState =
                    State.TERMINATED
                if (listener != null)
                    die(ImsReasonInfo())
            }
        }

        val subId = MtImsService.instance!!.subscriptionManager
            .getActiveSubscriptionInfoForSimSlotIndex(mSlotId).subscriptionId

        val telephonyManager = MtImsService.instance!!.telephonyManager.createForSubscriptionId(subId)

        // We have to do lots of complicated formatting stuff here because RIL returns different formats depending on the MCC-MNC
        Log.d(tag, "CC ${telephonyManager.networkCountryIso.toUpperCase()}")
        mProfile.setCallExtra(
            ImsCallProfile.EXTRA_OI, PhoneNumberUtils.formatNumberToE164(
                call.number,
                (telephonyManager.networkCountryIso
                    ?: telephonyManager.simCountryIso).toUpperCase()
            )
        )

        Log.d(tag, "Using OI ${Rlog.pii(tag, mProfile.getCallExtra(ImsCallProfile.EXTRA_OI))} for profile")

        mProfile.setCallExtraInt(ImsCallProfile.EXTRA_OIR, call.numberPresentation)
        mProfile.setCallExtra(
            ImsCallProfile.EXTRA_CNA,
            if (call.name.isEmpty()) mProfile.getCallExtra(ImsCallProfile.EXTRA_OI) else call.name
        )
        mProfile.setCallExtraInt(ImsCallProfile.EXTRA_CNAP, call.namePresentation)

        if (lastState == mState /*state unchanged*/ && call.state != 6 /*END*/ && call != rilImsCall && listener != null) {
            listener!!.callSessionUpdated(mProfile)
        }
        rilImsCall = call
    }

    private fun die(reason: ImsReasonInfo) {
        if (rilImsCall != null)
            calls.remove(rilImsCall!!.index)
        awaitingIdFromRIL.remove(mCallee)
        mState = State.TERMINATED
        if (listener != null) {
            listener!!.callSessionTerminated(reason)
        }
    }

    fun notifyEnded() {
        die(ImsReasonInfo())
    }

    override fun setListener(listener: ImsCallSessionListener?) {
        this.listener = listener
    }

    override fun getCallId(): String {
        return "slot" + mSlotId + "id" + if (rilImsCall == null) "unknown!" + Integer.toString(mCount) else rilImsCall!!.index
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

    fun notifyConfDone(call: Call) {
        listener!!.callSessionMergeComplete(MtImsCallSession(mSlotId, mProfile, call))
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

        }, mSlotId))
    }


    companion object {
        const val tag = "MtImsCallSession"

        val awaitingIdFromRIL = ConcurrentHashMap<String, MtImsCallSession>()
        val calls = ConcurrentHashMap<Int, MtImsCallSession>()

        private var sCount = 0

        val sCallsLock = Object()
    }
}