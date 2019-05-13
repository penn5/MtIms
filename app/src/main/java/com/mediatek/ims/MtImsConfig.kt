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

    init {
        // We support VoLTE by default.
        configInt[ImsConfig.ConfigConstants.VLT_SETTING_ENABLED] = ImsConfig.FeatureValueConstants.ON
        // We don't yet support WFC
        //configInt[ImsConfig.ConfigConstants.VOICE_OVER_WIFI_SETTING_ENABLED] = ImsConfig.FeatureValueConstants.ON
    }

    override fun setConfig(item: Int, value: Int): Int {
        configInt[item] = value
        Log.d(tag, "Setting config int $item=$value")
        if (item == ImsConfig.ConfigConstants.VOLTE_USER_OPT_IN_STATUS)
            RilHolder.getRadio(mSlotId).setImsVoiceEnable(RilHolder.callback({ radioResponseInfo: RadioResponseInfo, data: Array<out Any?> ->
                if (radioResponseInfo.error != 0) {
                    Rlog.e(tag, "Failed to setImsVoiceEnable to user optin status, $radioResponseInfo $data")
                } else {
                    Rlog.d(tag, "setImsVoiceEnable success for user optin status")
                    notifyProvisionedValueChanged(item, value)
                }
            }, mSlotId), value > 0)
        else
            notifyProvisionedValueChanged(item, value)
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