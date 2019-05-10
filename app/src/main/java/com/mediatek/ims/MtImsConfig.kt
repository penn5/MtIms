package com.mediatek.ims

import android.annotation.NonNull
import android.telephony.ims.stub.ImsConfigImplBase
import com.android.ims.ImsConfig
import java.util.concurrent.ConcurrentHashMap

class MtImsConfig(val mSlotId: Int) : ImsConfigImplBase() {
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
        notifyProvisionedValueChanged(item, value)
        return ImsConfig.OperationStatusConstants.SUCCESS
    }

    override fun setConfig(item: Int, value: String): Int {
        configString[item] = value
        notifyProvisionedValueChanged(item, value)
        return ImsConfig.OperationStatusConstants.SUCCESS
    }

    override fun getConfigInt(@NonNull item: Int): Int {
        return configInt[item] ?: ImsConfig.FeatureValueConstants.ERROR

    }

    override fun getConfigString(item: Int): String? {
        return configString[item]
    }
}