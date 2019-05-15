package com.mediatek.ims

import android.hardware.radio.V1_0.*
import android.hardware.radio.V1_1.KeepaliveStatus
import android.os.Bundle
import android.telephony.Rlog
import android.telephony.ims.ImsCallProfile
import android.util.Log
import com.android.ims.ImsManager
import vendor.mediatek.hardware.radio.V2_0.CallForwardInfoEx
import vendor.mediatek.hardware.radio.V2_0.IImsRadioResponse
import java.util.*

class MtImsRadioResponseV2_0(val mSlotId: Int) : IImsRadioResponse.Stub() {
    override fun setCarrierInfoForImsiEncryptionResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun stopNetworkScanResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getColpResponse(p0: RadioResponseInfo?, p1: Int, p2: Int) {
        onResponse(p0, p1, p2)
    }

    override fun runGbaAuthenticationResponse(p0: RadioResponseInfo?, p1: ArrayList<String>?) {
        onResponse(p0, p1)
    }

    override fun startNetworkScanResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getColrResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0, p1)
    }

    override fun setModemImsCfgResponse(p0: RadioResponseInfo?, p1: String?) {
        onResponse(p0, p1)
    }

    override fun queryCallForwardInTimeSlotStatusResponse(p0: RadioResponseInfo?, p1: ArrayList<CallForwardInfoEx>?) {
        onResponse(p0, p1)
    }

    override fun stopKeepaliveResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setClipResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setColrResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setCallForwardInTimeSlotResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setColpResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setSimCardPowerResponse_1_1(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun startKeepaliveResponse(p0: RadioResponseInfo?, p1: KeepaliveStatus?) {
        onResponse(p0, p1)
    }

    override fun sendCdmaSmsResponse(p0: RadioResponseInfo?, p1: SendSmsResult?) {
        onResponse(p0, p1)
    }

    override fun imsBearerDeactivationDoneResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun sendSmsResponse(p0: RadioResponseInfo?, p1: SendSmsResult?) {
        onResponse(p0, p1)
    }

    override fun setFacilityLockForAppResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0, p1)
    }

    override fun getCdmaBroadcastConfigResponse(p0: RadioResponseInfo?, p1: ArrayList<CdmaBroadcastSmsConfigInfo>?) {
        onResponse(p0, p1)
    }

    override fun setUiccSubscriptionResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getRadioCapabilityResponse(p0: RadioResponseInfo?, p1: RadioCapability?) {
        onResponse(p0, p1)
    }

    override fun pullCallResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun sendTerminalResponseToSimResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun nvResetConfigResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun reportStkServiceIsRunningResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setCallIndicationResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getDeviceIdentityResponse(p0: RadioResponseInfo?, p1: String?, p2: String?, p3: String?, p4: String?) {
        onResponse(p0, p1, p2, p3)
    }

    override fun requestShutdownResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setVolteEnableResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun nvWriteCdmaPrlResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setIndicationFilterResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun sendSMSExpectMoreResponse(p0: RadioResponseInfo?, p1: SendSmsResult?) {
        onResponse(p0, p1)
    }

    override fun setImsCallStatusResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getIMSIForAppResponse(p0: RadioResponseInfo?, p1: String?) {
        onResponse(p0, p1)
    }

    override fun handleStkCallSetupRequestFromSimResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun separateConnectionResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setGsmBroadcastConfigResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun nvWriteItemResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setBarringPasswordResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun forceReleaseCallResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun imsBearerActivationDoneResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setBandModeResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun changeIccPin2ForAppResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0, p1)
    }

    override fun setAllowedCarriersResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0, p1)
    }

    override fun acknowledgeLastIncomingCdmaSmsResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun imsEctCommandResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setCellInfoListRateResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setupDataCallResponse(p0: RadioResponseInfo?, p1: SetupDataCallResult?) {
        onResponse(p0, p1)
    }

    override fun imsDeregNotificationResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun sendCDMAFeatureCodeResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setGsmBroadcastActivationResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getCallForwardStatusResponse(p0: RadioResponseInfo?, p1: ArrayList<CallForwardInfo>?) {
        onResponse(p0, p1)
    }

    override fun cancelPendingUssdResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setDataProfileResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun writeSmsToSimResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0, p1)
    }

    override fun addImsConferenceCallMemberResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getMuteResponse(p0: RadioResponseInfo?, p1: Boolean) {
        onResponse(p0, p1)
    }

    override fun setPreferredNetworkTypeResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getPreferredVoicePrivacyResponse(p0: RadioResponseInfo?, p1: Boolean) {
        onResponse(p0, p1)
    }

    override fun acknowledgeRequest(p0: Int) {
        onAck(p0)
    }

    override fun iccTransmitApduBasicChannelResponse(p0: RadioResponseInfo?, p1: IccIoResult?) {
        onResponse(p0, p1)
    }

    override fun getProvisionValueResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getImsRegistrationStateResponse(p0: RadioResponseInfo?, p1: Boolean, p2: Int) {
        onResponse(p0, p1, p2)
    }

    override fun getVoiceRadioTechnologyResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0, p1)
    }

    override fun sendUssdResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun voiceAcceptResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun rejectCallResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getGsmBroadcastConfigResponse(p0: RadioResponseInfo?, p1: ArrayList<GsmBroadcastSmsConfigInfo>?) {
        onResponse(p0, p1)
    }

    override fun getDataCallListResponse(p0: RadioResponseInfo?, p1: ArrayList<SetupDataCallResult>?) {
        onResponse(p0, p1)
    }

    override fun setNetworkSelectionModeManualResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setVilteEnableResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setLocationUpdatesResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getFacilityLockForAppResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0, p1)
    }

    override fun setSmscAddressResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setSimCardPowerResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun supplyIccPinForAppResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0, p1)
    }

    override fun changeIccPinForAppResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0, p1)
    }

    override fun vtDialResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setCallWaitingResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun switchWaitingOrHoldingAndActiveResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun stopDtmfResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setEccServiceCategoryResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getNetworkSelectionModeResponse(p0: RadioResponseInfo?, p1: Boolean) {
        onResponse(p0, p1)
    }

    override fun setProvisionValueResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setRadioCapabilityResponse(p0: RadioResponseInfo?, p1: RadioCapability?) {
        onResponse(p0, p1)
    }

    override fun getCurrentCallsResponse(p0: RadioResponseInfo?, arrayList: ArrayList<Call>?) {
        //onResponse(p0, p1)
        Rlog.d(tag, "getCurrentCallsResponse got ${arrayList?.size} calls!")
        synchronized(MtImsCallSession.sCallsLock) {
            val calls = ArrayList<Int>(arrayList!!.size)
            for (call in arrayList) {
                Log.d(tag, "calls list contains $call") //TODO PII
                // RIL sometimes gives us the leading +, so first try with one, and if its null, try again without the +.
                var session = MtImsCallSession.awaitingIdFromRIL["+" + call.number]
                if (session == null)
                    session = MtImsCallSession.awaitingIdFromRIL[call.number]
                if (session != null) {
                    Rlog.d(tag, "giving call id from ril.")
                    session.addIdFromRIL(call)
                }
                session = MtImsCallSession.calls[call.index]
                if (session == null) {
                    if (call.isMT) {
                        Log.d(tag, "Notifying MmTelFeature incoming call! $call") //TODO PII
                        // An incoming call that we have never seen before, tell the framework.
                    } else {
                        Log.e(tag, "Phantom Call!!!! $call") //TODO PII
                        MtImsCallSession.calls.forEach { (s, hwImsCallSession) ->
                            Rlog.d(
                                tag,
                                "Phantom debugging got call in static calls " + hwImsCallSession.rilImsCall!! + " with number " + s
                            )
                        } //TODO PII
                        MtImsCallSession.awaitingIdFromRIL.forEach { (s, hwImsCallSession) ->
                            Rlog.d(
                                tag,
                                "Phantom debugging got call in static awaiting " + hwImsCallSession.mCallee + " with number " + s
                            )
                        }
                        // Someone has been talking to AT... naughty.
                    }
                    val extras = Bundle()
                    val callSession = MtImsCallSession(mSlotId, ImsCallProfile(), call)
                    extras.putInt(ImsManager.EXTRA_PHONE_ID, mSlotId)
                    extras.putString(ImsManager.EXTRA_CALL_ID, callSession.callId)
                    extras.putBoolean(
                        ImsManager.EXTRA_IS_UNKNOWN_CALL,
                        !call.isMT
                    ) // A new outgoing call should never happen. Someone is playing with AT commands or talking to the modem.
                    MtImsService.instance!!.createMmTelFeature(mSlotId).notifyIncomingCall(callSession, extras)


                } else {
                    // Existing call, update it's data.
                    session.updateCall(call)
                }
                if (call.isMpty && call.state == 2) { // Dialing & Multiparty
                    // It is a new conference call being added.
                    for (confSession in MtImsCallSession.calls.values) {
                        if (confSession.isMultiparty) {
                            Rlog.d(tag, "adding call " + call.index + " to conference " + confSession.callId)
                            confSession.notifyConfDone(call)
                            break
                        }
                    }
                }
                calls.add(call.index)
            }
            for ((_, value) in MtImsCallSession.calls) {
                if (!calls.contains(value.rilImsCall!!.index)) {
                    try {
                        Rlog.d(tag, "notifying dead call " + value.rilImsCall!!) //TODO PII
                        value.notifyEnded()
                    } catch (e: RuntimeException) {
                        Rlog.e(tag, "error notifying dead call!", e)
                    }

                }
            }
        }
    }

    override fun setViWifiEnableResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setTTYModeResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getCdmaSubscriptionSourceResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0, p1)
    }

    override fun getClirResponse(p0: RadioResponseInfo?, p1: Int, p2: Int) {
        onResponse(p0, p1, p2)
    }

    override fun getCdmaRoamingPreferenceResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0, p1)
    }

    override fun videoCallAcceptResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun startLceServiceResponse(p0: RadioResponseInfo?, p1: LceStatusInfo?) {
        onResponse(p0, p1)
    }

    override fun resumeCallResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun acknowledgeIncomingGsmSmsWithPduResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun updateImsRegistrationStatusResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setImsRtpReportResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setSuppServiceNotificationsResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun dialResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getDataRegistrationStateResponse(
        p0: RadioResponseInfo?,
        p1: DataRegStateResult?
    ) {
        onResponse(p0, p1)
    }

    override fun iccTransmitApduLogicalChannelResponse(p0: RadioResponseInfo?, p1: IccIoResult?) {
        onResponse(p0, p1)
    }

    override fun setCallForwardResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun nvReadItemResponse(p0: RadioResponseInfo?, p1: String?) {
        onResponse(p0, p1)
    }

    override fun getHardwareConfigResponse(p0: RadioResponseInfo?, p1: ArrayList<HardwareConfig>?) {
        onResponse(p0, p1)
    }

    override fun cancelUssiResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getModemActivityInfoResponse(p0: RadioResponseInfo?, p1: ActivityStatsInfo?) {
        onResponse(p0, p1)
    }

    override fun getLastCallFailCauseResponse(p0: RadioResponseInfo?, p1: LastCallFailCauseInfo?) {
        onResponse(p0, p1)
    }

    override fun setWfcEnableResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun conferenceDialResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setImscfgResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setDataAllowedResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setWfcProfileResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setImsVoiceEnableResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getTTYModeResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0, p1)
    }

    override fun hangupAllResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setImsRegistrationReportResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getSignalStrengthResponse(p0: RadioResponseInfo?, p1: SignalStrength?) {
        onResponse(p0, p1)
    }

    override fun getVoiceRegistrationStateResponse(
        p0: RadioResponseInfo?,
        p1: VoiceRegStateResult?
    ) {
        onResponse(p0, p1)
    }

    override fun getClipResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0, p1)
    }

    override fun iccIOForAppResponse(p0: RadioResponseInfo?, p1: IccIoResult?) {
        onResponse(p0, p1)
    }

    override fun pullLceDataResponse(p0: RadioResponseInfo?, p1: LceDataInfo?) {
        onResponse(p0, p1)
    }

    override fun setCdmaRoamingPreferenceResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setNetworkSelectionModeAutomaticResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getIccCardStatusResponse(p0: RadioResponseInfo?, p1: CardStatus?) {
        onResponse(p0, p1)
    }

    override fun removeImsConferenceCallMemberResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun deactivateDataCallResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getOperatorResponse(p0: RadioResponseInfo?, p1: String?, p2: String?, p3: String?) {
        onResponse(p0, p1, p2, p3)
    }

    override fun sendBurstDtmfResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun deleteSmsOnRuimResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun supplyNetworkDepersonalizationResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0, p1)
    }

    override fun replaceVtCallResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setRadioPowerResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun requestIsimAuthenticationResponse(p0: RadioResponseInfo?, p1: String?) {
        onResponse(p0, p1)
    }

    override fun sendDtmfResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getAllowedCarriersResponse(p0: RadioResponseInfo?, p1: Boolean, p2: CarrierRestrictions?) {
        onResponse(p0, p1, p2)
    }

    override fun getSmscAddressResponse(p0: RadioResponseInfo?, p1: String?) {
        onResponse(p0, p1)
    }

    override fun getNeighboringCidsResponse(p0: RadioResponseInfo?, p1: ArrayList<NeighboringCell>?) {
        onResponse(p0, p1)
    }

    override fun getCallWaitingResponse(p0: RadioResponseInfo?, p1: Boolean, p2: Int) {
        onResponse(p0, p1, p2)
    }

    override fun exitEmergencyCallbackModeResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun requestIccSimAuthenticationResponse(p0: RadioResponseInfo?, p1: IccIoResult?) {
        onResponse(p0, p1)
    }

    override fun hangupWaitingOrBackgroundResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setCdmaBroadcastActivationResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun holdCallResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun conferenceResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun sendEnvelopeResponse(p0: RadioResponseInfo?, p1: String?) {
        onResponse(p0, p1)
    }

    override fun iccCloseLogicalChannelResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun sendDeviceStateResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setImsVideoEnableResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun supplyIccPukForAppResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0, p1)
    }

    override fun setClirResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun deleteSmsOnSimResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun sendEnvelopeWithStatusResponse(p0: RadioResponseInfo?, p1: IccIoResult?) {
        onResponse(p0, p1)
    }

    override fun acceptCallResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setPreferredVoicePrivacyResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun hangupConnectionResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun emergencyDialResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun supplyIccPin2ForAppResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0, p1)
    }

    override fun sendUssiResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setImsEnableResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun explicitCallTransferResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getAvailableBandModesResponse(p0: RadioResponseInfo?, p1: ArrayList<Int>?) {
        onResponse(p0, p1)
    }

    override fun reportSmsMemoryStatusResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setInitialAttachApnResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun vtDialWithSipUriResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun sendImsSmsResponse(p0: RadioResponseInfo?, p1: SendSmsResult?) {
        onResponse(p0, p1)
    }

    override fun getBasebandVersionResponse(p0: RadioResponseInfo?, p1: String?) {
        onResponse(p0, p1)
    }

    override fun supplyIccPuk2ForAppResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0, p1)
    }

    override fun acknowledgeLastIncomingGsmSmsResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun stopLceServiceResponse(p0: RadioResponseInfo?, p1: LceStatusInfo?) {
        onResponse(p0, p1)
    }

    override fun hangupForegroundResumeBackgroundResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setVoiceDomainPreferenceResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setCdmaBroadcastConfigResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getCellInfoListResponse(p0: RadioResponseInfo?, p1: ArrayList<CellInfo>?) {
        onResponse(p0, p1)
    }

    override fun getAvailableNetworksResponse(p0: RadioResponseInfo?, p1: ArrayList<OperatorInfo>?) {
        onResponse(p0, p1)
    }

    override fun setCdmaSubscriptionSourceResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setMuteResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun writeSmsToRuimResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0, p1)
    }

    override fun getPreferredNetworkTypeResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0, p1)
    }

    override fun iccOpenLogicalChannelResponse(p0: RadioResponseInfo?, p1: Int, p2: ArrayList<Byte>?) {
        onResponse(p0, p1, p2)
    }

    override fun startDtmfResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getCDMASubscriptionResponse(
        p0: RadioResponseInfo?,
        p1: String?,
        p2: String?,
        p3: String?,
        p4: String?,
        p5: String?
    ) {
        onResponse(p0, p1, p2, p3, p4, p5)
    }

    override fun dialWithSipUriResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    private fun onResponse(radioResponseInfo: RadioResponseInfo?, vararg args: Any?) {
        if (radioResponseInfo == null)
            Rlog.w(tag, "rRI null in onResponse!!!")
        else {
            Rlog.v(tag, "Finshed ${radioResponseInfo.serial}")
            RilHolder.triggerCB(radioResponseInfo.serial, radioResponseInfo, *args)
        }
    }

    private fun onAck(serial: Int) {
        Rlog.v(tag, "Ack'd $serial")
    }

    companion object {
        const val tag = "MtImsRadioResponseV1_1"
    }
}