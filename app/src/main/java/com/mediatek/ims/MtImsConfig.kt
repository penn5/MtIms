package com.mediatek.ims

import android.annotation.NonNull
import android.hardware.radio.V1_0.RadioResponseInfo
import android.telephony.Rlog
import android.telephony.ims.stub.ImsConfigImplBase
import android.util.Log
import com.android.ims.ImsConfig
import java.util.concurrent.ConcurrentHashMap

class MtImsConfig(private val mSlotId: Int) : ImsConfigImplBase() {
    private val configInt = ConcurrentHashMap<Int, Int>()
    private val configString = ConcurrentHashMap<Int, String>()

    override fun setConfig(item: Int, value: Int): Int {
        configInt[item] = value
        Log.d(tag, "Setting config int $item=$value")
        when (item) {
            ImsConfig.ConfigConstants.VLT_SETTING_ENABLED -> {
                RilHolder.getRadio(mSlotId)
                    .setVolteEnable(RilHolder.callback({ radioResponseInfo: RadioResponseInfo, data: Array<out Any?> ->
                        if (radioResponseInfo.error != 0) {
                            Rlog.e(tag, "Failed to setVolteEnable to user optin status, $radioResponseInfo $data")
                        } else {
                            Rlog.d(tag, "setVolteEnable success for user optin status")
                        }
                        RilHolder.getRadio(mSlotId)
                            .setImsVoiceEnable(RilHolder.callback({ radioResponseInfo2: RadioResponseInfo, data2: Array<out Any?> ->
                                if (radioResponseInfo2.error != 0) {
                                    Rlog.e(
                                        tag,
                                        "Failed to setImsVoiceEnable to user optin status, $radioResponseInfo2 $data2"
                                    )
                                } else {
                                    Rlog.d(tag, "setImsVoiceEnable success for user optin status")
                                    notifyProvisionedValueChanged(item, value)
                                }
                            }, mSlotId), value > 0)
                    }, mSlotId), value > 0)
            }
            ImsConfig.ConfigConstants.VOLTE_USER_OPT_IN_STATUS -> {
                RilHolder.getRadio(mSlotId)
                    .setVoiceDomainPreference(
                        RilHolder.callback({ radioResponseInfo: RadioResponseInfo, data: Array<out Any?> ->
                            if (radioResponseInfo.error != 0) {
                                Rlog.e(
                                    tag,
                                    "Failed to set vdp to user preference! VoLTE won't be used! $radioResponseInfo $data"
                                )
                            } else {
                                Rlog.d(tag, "Success to set vdp! :D")
                                notifyProvisionedValueChanged(item, value)
                            }
                        }, mSlotId), when (value) {
                            0 -> 2 // CS priority, PS fallback
                            else -> 4 // PS only
                        }
                    )
            }
            else -> notifyProvisionedValueChanged(item, value)
        }
        return ImsConfig.OperationStatusConstants.SUCCESS
    }

    override fun setConfig(item: Int, value: String): Int {
        configString[item] = value
        Log.d(tag, "Setting config string $item=$value")
        notifyProvisionedValueChanged(item, value)
        return ImsConfig.OperationStatusConstants.SUCCESS
    }

    override fun getConfigInt(@NonNull item: Int): Int {
        Log.d(tag, "Returning config int $item=${configInt[item]}")
        return configInt[item] ?: ImsConfig.FeatureValueConstants.ERROR
    }

    override fun getConfigString(item: Int): String? {
        Log.d(tag, "Returning config string $item=${configString[item]}")
        return configString[item]
    }
    companion object {
        const val tag = "MtImsConfig"
    }
}
