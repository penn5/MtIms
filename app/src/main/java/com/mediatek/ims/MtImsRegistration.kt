package com.mediatek.ims

import android.telephony.ims.ImsReasonInfo
import android.telephony.ims.stub.ImsRegistrationImplBase
import com.android.ims.ImsConfig

class MtImsRegistration(val mSlotId: Int) : ImsRegistrationImplBase() {

    // BEWARE FUTURE ME: https://android-review.googlesource.com/c/platform/frameworks/base/+/809459 is changing this big-time in AOSP Q

    fun notifyRegistered(@ImsRegistrationTech imsRadioTech: Int) {
        this.onRegistered(imsRadioTech)
        MtImsService.instance!!.getConfig(mSlotId).setConfig(ImsConfig.ConfigConstants.VLT_SETTING_ENABLED, 1)
    }

    fun notifyRegistering(@ImsRegistrationTech imsRadioTech: Int) {
        this.onRegistering(imsRadioTech)
    }

    fun notifyDeregistered(info: ImsReasonInfo, @ImsRegistrationTech imsRadioTech: Int) {
        this.onDeregistered(info)
    }
}
