package com.mediatek.ims

import android.hardware.radio.V1_0.Dial


class IRadioDelegator {
    private var mIRadio1: vendor.mediatek.hardware.radio.V1_1.IRadio? = null
    private var mIRadio2: vendor.mediatek.hardware.radio.V2_0.IRadio? = null
    private var mIRadio3: vendor.mediatek.hardware.radio.V3_0.IRadio? = null
    fun setIRadio1(iRadio1: vendor.mediatek.hardware.radio.V1_1.IRadio) {
        mIRadio1 = iRadio1
    }

    fun setIRadio2(iRadio2: vendor.mediatek.hardware.radio.V2_0.IRadio) {
        mIRadio2 = iRadio2
    }

    fun setIRadio3(iRadio3: vendor.mediatek.hardware.radio.V3_0.IRadio) {
        mIRadio3 = iRadio3
    }

    fun ensureSet() {
        if ((mIRadio1 == null) and (mIRadio2 == null) and (mIRadio3 == null))
            throw NoSuchElementException()
    }

    // Dirty but it works :D
    fun setResponseFunctions(
        response: android.hardware.radio.V1_0.IRadioResponse?,
        indication: android.hardware.radio.V1_0.IRadioIndication?
    ) {
        mIRadio3?.setResponseFunctions(
            response as? vendor.mediatek.hardware.radio.V3_0.IRadioResponse,
            indication as? vendor.mediatek.hardware.radio.V3_0.IRadioIndication
        )
        mIRadio2?.setResponseFunctions(
            response as? vendor.mediatek.hardware.radio.V2_0.IRadioResponse,
            indication as? vendor.mediatek.hardware.radio.V2_0.IRadioIndication
        )
        mIRadio1?.setResponseFunctions(
            response as? vendor.mediatek.hardware.radio.V1_1.IRadioResponse,
            indication as? vendor.mediatek.hardware.radio.V1_1.IRadioIndication
        )
    }

    fun setResponseFunctionsIms(
        response: android.hardware.radio.V1_0.IRadioResponse?,
        indication: android.hardware.radio.V1_0.IRadioIndication?
    ) {
        mIRadio3?.setResponseFunctionsIms(
            response as? vendor.mediatek.hardware.radio.V3_0.IImsRadioResponse,
            indication as? vendor.mediatek.hardware.radio.V3_0.IImsRadioIndication
        )
        mIRadio2?.setResponseFunctionsIms(
            response as? vendor.mediatek.hardware.radio.V2_0.IImsRadioResponse,
            indication as? vendor.mediatek.hardware.radio.V2_0.IImsRadioIndication
        )
        mIRadio1?.setResponseFunctionsIms(
            response as? vendor.mediatek.hardware.radio.V1_1.IImsRadioResponse,
            indication as? vendor.mediatek.hardware.radio.V1_1.IImsRadioIndication
        )
    }
    fun setImscfg(
        serial: Int,
        volte: Boolean,
        vowifi: Boolean,
        vilte: Boolean,
        viwifi: Boolean,
        sms: Boolean,
        eims: Boolean
    ) {
        mIRadio3?.setImscfg(serial, volte, vowifi, vilte, viwifi, sms, eims)
        mIRadio2?.setImscfg(serial, volte, vowifi, vilte, viwifi, sms, eims)
        mIRadio1?.setImscfg(serial, volte, vowifi, vilte, viwifi, sms, eims)
    }

    fun setImsEnable(serial: Int, enable: Boolean) {
        mIRadio3?.setImsEnable(serial, enable)
        mIRadio2?.setImsEnable(serial, enable)
        mIRadio1?.setImsEnable(serial, enable)
    }

    fun getCurrentCalls(serial: Int) {
        mIRadio3?.getCurrentCalls(serial)
        mIRadio2?.getCurrentCalls(serial)
        mIRadio1?.getCurrentCalls(serial)
    }

    fun setImsVoiceEnable(serial: Int, value: Boolean) {
        mIRadio3?.setImsVoiceEnable(serial, value)
        mIRadio2?.setImsVoiceEnable(serial, value)
        mIRadio1?.setImsVoiceEnable(serial, value)
    }

    fun dial(serial: Int, callInfo: Dial) {
        mIRadio3?.dial(serial, callInfo)
        mIRadio2?.dial(serial, callInfo)
        mIRadio1?.dial(serial, callInfo)
    }

    fun acceptCall(serial: Int) {
        mIRadio3?.acceptCall(serial)
        mIRadio2?.acceptCall(serial)
        mIRadio1?.acceptCall(serial)
    }

    fun rejectCall(serial: Int) {
        mIRadio3?.rejectCall(serial)
        mIRadio2?.rejectCall(serial)
        mIRadio1?.rejectCall(serial)
    }

    fun setMute(serial: Int, muted: Boolean) {
        mIRadio3?.setMute(serial, muted)
        mIRadio2?.setMute(serial, muted)
        mIRadio1?.setMute(serial, muted)
    }

    fun hangup(serial: Int, index: Int) {
        mIRadio3?.hangup(serial, index)
        mIRadio2?.hangup(serial, index)
        mIRadio1?.hangup(serial, index)
    }

    fun holdCall(serial: Int, index: Int) {
        mIRadio3?.holdCall(serial, index)
        mIRadio2?.holdCall(serial, index)
        mIRadio1?.holdCall(serial, index)
    }

    fun resumeCall(serial: Int, index: Int) {
        mIRadio3?.resumeCall(serial, index)
        mIRadio2?.resumeCall(serial, index)
        mIRadio1?.resumeCall(serial, index)
    }
}