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
                RilHolder.getRadio(mSlotId).setConfig(-1, ImsConfig.FeatureConstants.FEATURE_TYPE_VOICE_OVER_LTE, value.toString())
                RilHolder.getRadio(mSlotId)
                    .setImsVoiceEnable(RilHolder.callback({ radioResponseInfo: RadioResponseInfo, data: Array<out Any?> ->
                        if (radioResponseInfo.error != 0) {
                            Rlog.e(tag, "Failed to setImsVoiceEnable to user optin status, $radioResponseInfo $data")
                        } else {
                            Rlog.d(tag, "setImsVoiceEnable success for user optin status")
                        }
                    }, mSlotId), value > 0)
                RilHolder.getRadio(mSlotId)
                    .setVolteEnable(RilHolder.callback({ radioResponseInfo: RadioResponseInfo, data: Array<out Any?> ->
                        if (radioResponseInfo.error != 0) {
                            Rlog.e(tag, "Failed to setVolteEnable to user optin status, $radioResponseInfo $data")
                        } else {
                            Rlog.d(tag, "setVolteEnable success for user optin status")
                        }

                        RilHolder.getRadio(mSlotId).setVoiceDomainPreference(
                            RilHolder.callback({ radioResponseInfo2: RadioResponseInfo, data2: Array<out Any?> ->
                                if (radioResponseInfo2.error != 0) {
                                    Rlog.e(tag, "Failed to setVoiceDomainPreference, $radioResponseInfo2 $data2")
                                } else {
                                    Rlog.d(tag, "setVoiceDomainPreference success yay")
                                    notifyProvisionedValueChanged(item, value)
                                }
                            }, mSlotId), value + 1
                        ) // User opt in status is 0 (false/CS) or 1 (true/PS) but android.telephony.NetworkRegistrationState says we should use 1 (false/CS) or 2 (true/PS)
                    }, mSlotId), value > 0)
            }
            ImsConfig.ConfigConstants.VOLTE_USER_OPT_IN_STATUS -> {
                RilHolder.getRadio(mSlotId).setConfig(-1,ImsConfig.FeatureConstants.FEATURE_TYPE_VOICE_OVER_LTE, value.toString())
            }
            else -> notifyProvisionedValueChanged(item, value)
        }
        return ImsConfig.OperationStatusConstants.SUCCESS
    }

    override fun setConfig(item: Int, value: String): Int {
        configString[item] = value
        RilHolder.getRadio(mSlotId).setConfig(-1, item, value)
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
