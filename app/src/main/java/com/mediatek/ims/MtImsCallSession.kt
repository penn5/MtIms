package com.mediatek.ims

import android.hardware.radio.V1_0.RadioResponseInfo
import android.telephony.Rlog
import android.telephony.ims.ImsCallProfile
import android.telephony.ims.ImsCallSessionListener
import android.telephony.ims.ImsReasonInfo
import android.telephony.ims.ImsStreamMediaProfile
import android.telephony.ims.stub.ImsCallSessionImplBase

class MtImsCallSession

/* For outgoing (MO) calls */(slotId: Int, profile: ImsCallProfile) : ImsCallSessionImplBase() {

    private var mSlotId: Int
    private var mIndex = -1
    private var mNumber = ""
    private var mConference = false
    private var mState = State.INVALID
    private var mProfile: ImsCallProfile
    private var mCount: Int
    private var listener: ImsCallSessionListener? = null
    private var mInCall = false

    init {
        mSlotId = slotId
        mProfile = profile
        synchronized(sCountLock) {
            mCount = sCount++
        }
    }

    fun giveIndexFromRIL(index: Int) {
        if (mIndex < 0)
            mIndex = index
    }

    // For incoming (MT) calls
    constructor(slotId: Int, profile: ImsCallProfile, index: Int, oi: String, isConference: Boolean) : this(
        slotId,
        profile
    ) {
        mSlotId = slotId
        mProfile = profile
        mIndex = index
        mNumber = oi
        mProfile.setCallExtra(ImsCallProfile.EXTRA_OI, oi)
        mProfile.setCallExtraInt(ImsCallProfile.EXTRA_OIR, ImsCallProfile.OIR_DEFAULT)
        mConference = isConference
        mState = State.ESTABLISHED
        CallSessionTracker.addCallWithIndex(mIndex, this)
        synchronized(sCountLock) {
            mCount = sCount++
        }
    }

    override fun setListener(listener: ImsCallSessionListener?) {
        this.listener = listener
    }

    override fun getCallId(): String {
        return "slot" + mSlotId + "id" + if (mIndex < 0) "unknown!" + Integer.toString(mCount) else mIndex
    }

    override fun getCallProfile(): ImsCallProfile {
        return mProfile
    }

    override fun getRemoteCallProfile(): ImsCallProfile {
        return mProfile
    }

    override fun getLocalCallProfile(): ImsCallProfile {
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


    companion object {
        const val tag = "MtImsCallSession"

        private var sCount = 0
        private var sCountLock = Object()
    }
}