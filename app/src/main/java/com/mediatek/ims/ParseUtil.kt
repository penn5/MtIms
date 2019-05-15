package com.mediatek.ims

import android.os.Bundle
import android.telephony.Rlog
import android.telephony.ims.ImsCallProfile
import com.android.ims.ImsManager

object ParseUtil {
    fun parseCallInfoIndication(slotId: Int, data: ArrayList<String>?) {
        data?.let {
            if (data.size > 0) {
                val callId = data[0]
                val msgType = data[1].toInt()
                val callMode = if (data[5] == "") MtkConstants.INVALID_CALL else data[5].toInt()
                val callNumber = data[6]
                // p1=msgType, p2=callMode, p3=callId, p4=callNumber

                val conference = when (callMode) {
                    MtkConstants.IMS_VOICE_CONF -> true
                    MtkConstants.IMS_VOICE_CONF_PARTS -> true
                    MtkConstants.IMS_VIDEO_CONF -> true
                    MtkConstants.IMS_VIDEO_CONF_PARTS -> true
                    else -> false
                }

                Rlog.d(
                    tag,
                    "callInfoIndication, callId=$callId, msgType=$msgType, callNumber=${Rlog.pii(
                        tag,
                        callNumber
                    )}, callMode=$callMode, conference=$conference"
                )

                when (msgType) {
                    MtkConstants.CALL_MSG_TYPE_MT -> {
                        Rlog.d(tag, "MT call!")
                        parseIncomingCallIndication(slotId, callId, callNumber)
                    }
                    MtkConstants.CALL_MSG_TYPE_ID_ASSIGN -> {
                        Rlog.d(tag, "Assign ID from RIL")
                        giveIdFromRIL(callNumber, callId)
                    }
                    MtkConstants.CALL_MSG_TYPE_ALERT -> {
                        Rlog.d(tag, "Ringing on other end!")
                    }
                    MtkConstants.CALL_MSG_TYPE_HELD -> {
                        Rlog.d(tag, "Phone HELD!")
                    }
                    MtkConstants.CALL_MSG_TYPE_DISCONNECTED -> {
                        Rlog.d(tag, "Call TERMINATED")
                        MtImsCallSession.calls.get(callId)!!.notifyEnded()
                    } // Call ended; delete
                }
            }
        }
    }

    fun parseIncomingCallIndication(slotId: Int, callId: String, number: String) {
        Rlog.d(tag, "Incoming call notification! $callId=${Rlog.pii(tag, number)}")
        val extras = Bundle()
        val callSession = MtImsCallSession(slotId, ImsCallProfile(), callId.toInt(), 4, number)
        extras.putInt(ImsManager.EXTRA_PHONE_ID, slotId)
        extras.putString(ImsManager.EXTRA_CALL_ID, callSession.callId)
        MtImsService.instance!!.createMmTelFeature(slotId).notifyIncomingCall(callSession, extras)
    }

    const val tag = "MtImsParseUtil"
}