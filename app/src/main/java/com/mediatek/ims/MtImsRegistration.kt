package com.mediatek.ims

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.telephony.ims.ImsReasonInfo
import android.telephony.ims.stub.ImsRegistrationImplBase
import com.android.ims.ImsConfig


class MtImsRegistration(val mSlotId: Int) : ImsRegistrationImplBase() {

    // BEWARE FUTURE ME: https://android-review.googlesource.com/c/platform/frameworks/base/+/809459 is changing this big-time in AOSP Q

    fun notifyRegistered(@ImsRegistrationTech imsRadioTech: Int) {
        this.onRegistered(imsRadioTech)
        MtImsService.instance!!.getConfig(mSlotId).setConfig(ImsConfig.ConfigConstants.VLT_SETTING_ENABLED, 1)
        MtImsService.instance!!.getConfig(mSlotId).setConfig(ImsConfig.ConfigConstants.VOLTE_USER_OPT_IN_STATUS, 1)
    }

    fun notifyRegistering(@ImsRegistrationTech imsRadioTech: Int) {
        this.onRegistering(imsRadioTech)
    }

    fun notifyDeregistered(info: ImsReasonInfo) {
        this.onDeregistered(info)
        MtImsService.instance!!.getConfig(mSlotId).setConfig(ImsConfig.ConfigConstants.VLT_SETTING_ENABLED, 0)
    }

    @SuppressLint("MissingPermission")
    fun imsEnableStart() {
        val subId = MtImsService.instance!!.subscriptionManager
            .getActiveSubscriptionInfoForSimSlotIndex(mSlotId).subscriptionId
        val builder = NetworkRequest.Builder()
        builder.addCapability(NetworkCapabilities.NET_CAPABILITY_EIMS)
        builder.addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        builder.setNetworkSpecifier(subId.toString())
        val nwRequest = builder.build()
        MtImsService.instance!!.connectivityManager.requestNetwork(nwRequest, ConnectivityManager.NetworkCallback())
        MtImsService.instance!!.getRegistration(mSlotId).notifyRegistered(REGISTRATION_TECH_LTE)
        MtImsService.instance!!.createMmTelFeature(mSlotId).setVoiceRegistered(true)
    }
}
