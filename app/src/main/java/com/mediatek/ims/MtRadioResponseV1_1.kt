package com.mediatek.ims

import android.hardware.radio.V1_0.*
import android.telephony.Rlog
import vendor.mediatek.hardware.radio.V1_1.*
import java.util.*

// Do nothing.

class MtRadioResponseV1_1(val mSlotId: Int) : vendor.mediatek.hardware.radio.V1_1.IRadioResponse.Stub() {
    override fun getRoamingEnableResponse(p0: RadioResponseInfo?, p1: ArrayList<Int>?) {
        onResponse(p0)
    }

    override fun getPOLCapabilityResponse(p0: RadioResponseInfo?, p1: ArrayList<Int>?) {
        onResponse(p0)
    }

    override fun sendCdmaSmsResponse(p0: RadioResponseInfo?, p1: SendSmsResult?) {
        onResponse(p0)
    }

    override fun syncDataSettingsToMdResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getATRResponse(p0: RadioResponseInfo?, p1: String?) {
        onResponse(p0)
    }

    override fun getSmsRuimMemoryStatusResponse(p0: RadioResponseInfo?, p1: SmsMemStatus?) {
        onResponse(p0)
    }

    override fun writePhbEntryResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun sendSmsResponse(p0: RadioResponseInfo?, p1: SendSmsResult?) {
        onResponse(p0)
    }

    override fun setFacilityLockForAppResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0)
    }

    override fun getSmsParametersResponse(p0: RadioResponseInfo?, p1: SmsParams?) {
        onResponse(p0)
    }

    override fun setRemoveRestrictEutranModeResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getCdmaBroadcastConfigResponse(p0: RadioResponseInfo?, p1: ArrayList<CdmaBroadcastSmsConfigInfo>?) {
        onResponse(p0)
    }

    override fun getDataCallListResponseEx(p0: RadioResponseInfo?, p1: ArrayList<MtkSetupDataCallResult>?) {
        onResponse(p0)
    }

    override fun setUiccSubscriptionResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getRadioCapabilityResponse(p0: RadioResponseInfo?, p1: RadioCapability?) {
        onResponse(p0)
    }

    override fun sendTerminalResponseToSimResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun vsimNotificationResponse(p0: RadioResponseInfo?, p1: VsimEvent?) {
        onResponse(p0)
    }

    override fun cancelAvailableNetworksResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun nvResetConfigResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun reportStkServiceIsRunningResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setNetworkSelectionModeManualWithActResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setCallIndicationResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getDeviceIdentityResponse(p0: RadioResponseInfo?, p1: String?, p2: String?, p3: String?, p4: String?) {
        onResponse(p0)
    }

    override fun requestShutdownResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setLteUplinkDataTransferResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun nvWriteCdmaPrlResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setIndicationFilterResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun queryNetworkLockResponse(
        p0: RadioResponseInfo?,
        p1: Int,
        p2: Int,
        p3: Int,
        p4: Int,
        p5: Int,
        p6: Int,
        p7: Int
    ) {
        onResponse(p0)
    }

    override fun queryPhbStorageInfoResponse(p0: RadioResponseInfo?, p1: ArrayList<Int>?) {
        onResponse(p0)
    }

    override fun sendSMSExpectMoreResponse(p0: RadioResponseInfo?, p1: SendSmsResult?) {
        onResponse(p0)
    }

    override fun readUPBAasListResponse(p0: RadioResponseInfo?, p1: ArrayList<String>?) {
        onResponse(p0)
    }

    override fun readPhbEntryResponse(p0: RadioResponseInfo?, p1: ArrayList<PhbEntryStructure>?) {
        onResponse(p0)
    }

    override fun setRoamingEnableResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun sendEmbmsAtCommandResponse(p0: RadioResponseInfo?, p1: String?) {
        onResponse(p0)
    }

    override fun getIMSIForAppResponse(p0: RadioResponseInfo?, p1: String?) {
        onResponse(p0)
    }

    override fun handleStkCallSetupRequestFromSimResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getGsmBroadcastLangsResponse(p0: RadioResponseInfo?, p1: String?) {
        onResponse(p0)
    }

    override fun getSmsMemStatusResponse(p0: RadioResponseInfo?, p1: SmsMemStatus?) {
        onResponse(p0)
    }

    override fun separateConnectionResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun currentStatusResponse(p0: RadioResponseInfo?) {
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

    override fun getColpResponse(p0: RadioResponseInfo?, p1: Int, p2: Int) {
        onResponse(p0)
    }

    override fun queryUPBCapabilityResponse(p0: RadioResponseInfo?, p1: ArrayList<Int>?) {
        onResponse(p0)
    }

    override fun setBandModeResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun changeIccPin2ForAppResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0)
    }

    override fun setAllowedCarriersResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0)
    }

    override fun setRxTestConfigResponse(p0: RadioResponseInfo?, p1: ArrayList<Int>?) {
        onResponse(p0)
    }

    override fun acknowledgeLastIncomingCdmaSmsResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun readPhoneBookEntryExtResponse(p0: RadioResponseInfo?, p1: ArrayList<PhbEntryExt>?) {
        onResponse(p0)
    }

    override fun setCellInfoListRateResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setupDataCallResponse(p0: RadioResponseInfo?, p1: SetupDataCallResult?) {
        onResponse(p0)
    }

    override fun sendCDMAFeatureCodeResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun removeCbMsgResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setGsmBroadcastActivationResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getCallForwardStatusResponse(p0: RadioResponseInfo?, p1: ArrayList<CallForwardInfo>?) {
        onResponse(p0)
    }

    override fun cancelPendingUssdResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun editUPBEntryResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun resetMdDataRetryCountResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setDataProfileResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun writeSmsToSimResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0)
    }

    override fun getAvailableNetworksWithActResponse(p0: RadioResponseInfo?, p1: ArrayList<OperatorInfoWithAct>?) {
        onResponse(p0)
    }

    override fun getMuteResponse(p0: RadioResponseInfo?, p1: Boolean) {
        onResponse(p0)
    }

    override fun setPreferredNetworkTypeResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getPreferredVoicePrivacyResponse(p0: RadioResponseInfo?, p1: Boolean) {
        onResponse(p0)
    }

    override fun acknowledgeRequest(p0: Int) {
    }

    override fun getColrResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0)
    }

    override fun iccTransmitApduBasicChannelResponse(p0: RadioResponseInfo?, p1: IccIoResult?) {
        onResponse(p0)
    }

    override fun getImsRegistrationStateResponse(p0: RadioResponseInfo?, p1: Boolean, p2: Int) {
        onResponse(p0)
    }

    override fun selectFemtocellResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setGsmBroadcastLangsResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getVoiceRadioTechnologyResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0)
    }

    override fun sendUssdResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun abortFemtocellListResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun handleStkCallSetupRequestFromSimWithResCodeResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun voiceAcceptResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun rejectCallResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getGsmBroadcastConfigResponse(p0: RadioResponseInfo?, p1: ArrayList<GsmBroadcastSmsConfigInfo>?) {
        onResponse(p0)
    }

    override fun getDataCallListResponse(p0: RadioResponseInfo?, p1: ArrayList<SetupDataCallResult>?) {
        onResponse(p0)
    }

    override fun setNetworkSelectionModeManualResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setLocationUpdatesResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getFacilityLockForAppResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0)
    }

    override fun setSmscAddressResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setSimCardPowerResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun supplyIccPinForAppResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0)
    }

    override fun queryUPBAvailableResponse(p0: RadioResponseInfo?, p1: ArrayList<Int>?) {
        onResponse(p0)
    }

    override fun changeIccPinForAppResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0)
    }

    override fun getApcInfoResponse(p0: RadioResponseInfo?, p1: ArrayList<Int>?) {
        onResponse(p0)
    }

    override fun vtDialResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun vsimOperationResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getPhoneBookMemStorageResponse(p0: RadioResponseInfo?, p1: PhbMemStorageResponse?) {
        onResponse(p0)
    }

    override fun setCallWaitingResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun switchWaitingOrHoldingAndActiveResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun reloadModemTypeResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun stopDtmfResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setEccServiceCategoryResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getNetworkSelectionModeResponse(p0: RadioResponseInfo?, p1: Boolean) {
        onResponse(p0)
    }

    override fun getRxTestResultResponse(p0: RadioResponseInfo?, p1: ArrayList<Int>?) {
        onResponse(p0)
    }

    override fun readUPBGasListResponse(p0: RadioResponseInfo?, p1: ArrayList<String>?) {
        onResponse(p0)
    }

    override fun setRadioCapabilityResponse(p0: RadioResponseInfo?, p1: RadioCapability?) {
        onResponse(p0)
    }

    override fun getCurrentCallsResponse(p0: RadioResponseInfo?, p1: ArrayList<Call>?) {
        onResponse(p0)
    }

    override fun setTTYModeResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getCdmaSubscriptionSourceResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0)
    }

    override fun setPhoneBookMemStorageResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getClirResponse(p0: RadioResponseInfo?, p1: Int, p2: Int) {
        onResponse(p0)
    }

    override fun getCdmaRoamingPreferenceResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0)
    }

    override fun startLceServiceResponse(p0: RadioResponseInfo?, p1: LceStatusInfo?) {
        onResponse(p0)
    }

    override fun acknowledgeIncomingGsmSmsWithPduResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getGsmBroadcastActivationRsp(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0)
    }

    override fun setSuppServiceNotificationsResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun dialResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getDataRegistrationStateResponse(p0: RadioResponseInfo?, p1: DataRegStateResult?) {
        onResponse(p0)
    }

    override fun iccTransmitApduLogicalChannelResponse(p0: RadioResponseInfo?, p1: IccIoResult?) {
        onResponse(p0)
    }

    override fun setCallForwardResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun writePhoneBookEntryExtResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun nvReadItemResponse(p0: RadioResponseInfo?, p1: String?) {
        onResponse(p0)
    }

    override fun setTrmResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getHardwareConfigResponse(p0: RadioResponseInfo?, p1: ArrayList<HardwareConfig>?) {
        onResponse(p0)
    }

    override fun getModemActivityInfoResponse(p0: RadioResponseInfo?, p1: ActivityStatsInfo?) {
        onResponse(p0)
    }

    override fun getLastCallFailCauseResponse(p0: RadioResponseInfo?, p1: LastCallFailCauseInfo?) {
        onResponse(p0)
    }

    override fun getCurrentPOLListResponse(p0: RadioResponseInfo?, p1: ArrayList<String>?) {
        onResponse(p0)
    }

    override fun setDataAllowedResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getTTYModeResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0)
    }

    override fun hangupAllResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getSignalStrengthResponse(p0: RadioResponseInfo?, p1: SignalStrength?) {
        onResponse(p0)
    }

    override fun getVoiceRegistrationStateResponse(p0: RadioResponseInfo?, p1: VoiceRegStateResult?) {
        onResponse(p0)
    }

    override fun getClipResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0)
    }

    override fun iccIOForAppResponse(p0: RadioResponseInfo?, p1: IccIoResult?) {
        onResponse(p0)
    }

    override fun pullLceDataResponse(p0: RadioResponseInfo?, p1: LceDataInfo?) {
        onResponse(p0)
    }

    override fun readUPBGrpEntryResponse(p0: RadioResponseInfo?, p1: ArrayList<Int>?) {
        onResponse(p0)
    }

    override fun setDataOnToMDResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setupDataCallResponseEx(p0: RadioResponseInfo?, p1: MtkSetupDataCallResult?) {
        onResponse(p0)
    }

    override fun setCdmaRoamingPreferenceResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setFdModeResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setNetworkSelectionModeAutomaticResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun writeUPBGrpEntryResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getIccCardStatusResponse(p0: RadioResponseInfo?, p1: CardStatus?) {
        onResponse(p0)
    }

    override fun deactivateDataCallResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setEtwsResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getOperatorResponse(p0: RadioResponseInfo?, p1: String?, p2: String?, p3: String?) {
        onResponse(p0)
    }

    override fun sendBurstDtmfResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun deleteSmsOnRuimResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun supplyNetworkDepersonalizationResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0)
    }

    override fun setLteAccessStratumReportResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun replaceVtCallResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setSimPowerResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setRadioPowerResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun requestIsimAuthenticationResponse(p0: RadioResponseInfo?, p1: String?) {
        onResponse(p0)
    }

    override fun eccPreferredRatResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun sendDtmfResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun resetRadioResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun triggerModeSwitchByEccResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getAllowedCarriersResponse(p0: RadioResponseInfo?, p1: Boolean, p2: CarrierRestrictions?) {
        onResponse(p0)
    }

    override fun getSmscAddressResponse(p0: RadioResponseInfo?, p1: String?) {
        onResponse(p0)
    }

    override fun queryFemtoCellSystemSelectionModeResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0)
    }

    override fun getNeighboringCidsResponse(p0: RadioResponseInfo?, p1: ArrayList<NeighboringCell>?) {
        onResponse(p0)
    }

    override fun setApcModeResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setClipResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getCallWaitingResponse(p0: RadioResponseInfo?, p1: Boolean, p2: Int) {
        onResponse(p0)
    }

    override fun exitEmergencyCallbackModeResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun requestIccSimAuthenticationResponse(p0: RadioResponseInfo?, p1: IccIoResult?) {
        onResponse(p0)
    }

    override fun hangupWaitingOrBackgroundResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setCdmaBroadcastActivationResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun conferenceResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setResumeRegistrationResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun sendEnvelopeResponse(p0: RadioResponseInfo?, p1: String?) {
        onResponse(p0)
    }

    override fun iccCloseLogicalChannelResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun sendDeviceStateResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun supplyIccPukForAppResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0)
    }

    override fun setClirResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun deleteSmsOnSimResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun sendEnvelopeWithStatusResponse(p0: RadioResponseInfo?, p1: IccIoResult?) {
        onResponse(p0)
    }

    override fun acceptCallResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun readUPBEmailEntryResponse(p0: RadioResponseInfo?, p1: String?) {
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

    override fun readUPBAnrEntryResponse(p0: RadioResponseInfo?, p1: ArrayList<PhbEntryStructure>?) {
        onResponse(p0)
    }

    override fun setFemtoCellSystemSelectionModeResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun supplyIccPin2ForAppResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0)
    }

    override fun setNetworkLockResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun sendCnapResponse(p0: RadioResponseInfo?, p1: Int, p2: Int) {
        onResponse(p0)
    }

    override fun setModemPowerResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun readUPBSneEntryResponse(p0: RadioResponseInfo?, p1: String?) {
        onResponse(p0)
    }

    override fun explicitCallTransferResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setEccListResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun deleteUPBEntryResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getFemtocellListResponse(p0: RadioResponseInfo?, p1: ArrayList<String>?) {
        onResponse(p0)
    }

    override fun getAvailableBandModesResponse(p0: RadioResponseInfo?, p1: ArrayList<Int>?) {
        onResponse(p0)
    }

    override fun reportSmsMemoryStatusResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setSmsParametersResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setInitialAttachApnResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun sendImsSmsResponse(p0: RadioResponseInfo?, p1: SendSmsResult?) {
        onResponse(p0)
    }

    override fun getBasebandVersionResponse(p0: RadioResponseInfo?, p1: String?) {
        onResponse(p0)
    }

    override fun supplyIccPuk2ForAppResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0)
    }

    override fun acknowledgeLastIncomingGsmSmsResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun stopLceServiceResponse(p0: RadioResponseInfo?, p1: LceStatusInfo?) {
        onResponse(p0)
    }

    override fun syncApnTableResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun hangupForegroundResumeBackgroundResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setCdmaBroadcastConfigResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun storeModemTypeResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getCellInfoListResponse(p0: RadioResponseInfo?, p1: ArrayList<CellInfo>?) {
        onResponse(p0)
    }

    override fun getAvailableNetworksResponse(p0: RadioResponseInfo?, p1: ArrayList<OperatorInfo>?) {
        onResponse(p0)
    }

    override fun setCdmaSubscriptionSourceResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun setMuteResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun writeSmsToRuimResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0)
    }

    override fun getPreferredNetworkTypeResponse(p0: RadioResponseInfo?, p1: Int) {
        onResponse(p0)
    }

    override fun iccOpenLogicalChannelResponse(p0: RadioResponseInfo?, p1: Int, p2: ArrayList<Byte>?) {
        onResponse(p0)
    }

    override fun setPOLEntryResponse(p0: RadioResponseInfo?) {
        onResponse(p0)
    }

    override fun getPhoneBookStringsLengthResponse(p0: RadioResponseInfo?, p1: ArrayList<Int>?) {
        onResponse(p0)
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

    companion object {
        const val tag = "MtRadioResponseV2_0"
    }
}