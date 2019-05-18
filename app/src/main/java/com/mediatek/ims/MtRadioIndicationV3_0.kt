package com.mediatek.ims

import android.hardware.radio.V1_0.*
import android.hardware.radio.V1_1.KeepaliveStatus
import android.hardware.radio.V1_1.NetworkScanResult
import android.hardware.radio.V1_2.CellInfo
import android.hardware.radio.V1_2.LinkCapacityEstimate
import android.hardware.radio.V1_2.PhysicalChannelConfig
import android.hardware.radio.V1_2.SignalStrength
import vendor.mediatek.hardware.radio.V3_0.*
import java.util.*

class MtRadioIndicationV3_0(val mSlotId: Int) : vendor.mediatek.hardware.radio.V3_0.IRadioIndication.Stub() {
    override fun newSmsStatusReport(p0: Int, p1: ArrayList<Byte>?) {
    }

    override fun carrierInfoForImsiEncryption(p0: Int) {
    }

    override fun suppSvcNotify(p0: Int, p1: SuppSvcNotification?) {
    }

    override fun simRefresh(p0: Int, p1: SimRefreshResult?) {
    }

    override fun responseLteNetworkInfo(p0: Int, p1: Int) {
    }

    override fun currentSignalStrength(p0: Int, p1: android.hardware.radio.V1_0.SignalStrength?) {
    }

    override fun keepaliveStatus(p0: Int, p1: KeepaliveStatus?) {
    }

    override fun cdmaOtaProvisionStatus(p0: Int, p1: Int) {
    }

    override fun mdChangedApnInd(p0: Int, p1: Int) {
    }

    override fun onImsiRefreshDone(p0: Int) {
    }

    override fun onSimMeLockEvent(p0: Int) {
    }

    override fun simSmsStorageFull(p0: Int) {
    }

    override fun lceData(p0: Int, p1: LceDataInfo?) {
    }

    override fun dedicatedBearerModificationInd(p0: Int, p1: DedicateDataCall?) {
    }

    override fun speechCodecInfoIndication(p0: Int, p1: Int) {
    }

    override fun resetAttachApnInd(p0: Int) {
    }

    override fun onTxPowerStatusIndication(p0: Int, p1: ArrayList<Int>?) {
    }

    override fun onVirtualSimOn(p0: Int, p1: Int) {
    }

    override fun pcoDataAfterAttached(p0: Int, p1: PcoDataAttachedInfo?) {
    }

    override fun subscriptionStatusChanged(p0: Int, p1: Boolean) {
    }

    override fun exitEmergencyCallbackMode(p0: Int) {
    }

    override fun onSimPlugOut(p0: Int) {
    }

    override fun imsNetworkStateChanged(p0: Int) {
    }

    override fun onRemoveRestrictEutran(p0: Int) {
    }

    override fun incomingCallIndication(p0: Int, p1: IncomingCallNotification?) {
    }

    override fun onStkMenuReset(p0: Int) {
    }

    override fun onCardDetectedInd(p0: Int) {
    }

    override fun eMBMSSessionStatusIndication(p0: Int, p1: Int) {
    }

    override fun stkCallSetup(p0: Int, p1: Long) {
    }

    override fun rilConnected(p0: Int) {
    }

    override fun volteLteConnectionStatus(p0: Int, p1: ArrayList<Int>?) {
    }

    override fun confSRVCC(p0: Int, p1: ArrayList<Int>?) {
    }

    override fun onSimCommonSlotNoChanged(p0: Int) {
    }

    override fun networkScanResult(p0: Int, p1: NetworkScanResult?) {
    }

    override fun responseModulationInfoInd(p0: Int, p1: ArrayList<Int>?) {
    }

    override fun restrictedStateChanged(p0: Int, p1: Int) {
    }

    override fun radioCapabilityIndication(p0: Int, p1: RadioCapability?) {
    }

    override fun stkCallControlAlphaNotify(p0: Int, p1: String?) {
    }

    override fun newBroadcastSms(p0: Int, p1: ArrayList<Byte>?) {
    }

    override fun registrationSuspendedIndication(p0: Int, p1: ArrayList<Int>?) {
    }

    override fun onMccMncChanged(p0: Int, p1: String?) {
    }

    override fun triggerOtaSP(p0: Int) {
    }

    override fun dsbpStateChanged(p0: Int, p1: Int) {
    }

    override fun onPcoStatus(p0: Int, p1: ArrayList<Int>?) {
    }

    override fun networkRejectCauseInd(p0: Int, p1: ArrayList<Int>?) {
    }

    override fun networkStateChanged(p0: Int) {
    }

    override fun cipherIndication(p0: Int, p1: CipherNotification?) {
    }

    override fun smsReadyInd(p0: Int) {
    }

    override fun onSimRecovery(p0: Int, p1: Int) {
    }

    override fun onSupplementaryServiceIndication(p0: Int, p1: StkCcUnsolSsResult?) {
    }

    override fun currentSignalStrength_1_2(p0: Int, p1: SignalStrength?) {
    }

    override fun dedicatedBearerActivationInd(p0: Int, p1: DedicateDataCall?) {
    }

    override fun phbReadyNotification(p0: Int, p1: Int) {
    }

    override fun plmnChangedIndication(p0: Int, p1: ArrayList<String>?) {
    }

    override fun worldModeChangedIndication(p0: Int, p1: ArrayList<Int>?) {
    }

    override fun newSms(p0: Int, p1: ArrayList<Byte>?) {
    }

    override fun cdmaPrlChanged(p0: Int, p1: Int) {
    }

    override fun callStateChanged(p0: Int) {
    }

    override fun responsePsNetworkStateChangeInd(p0: Int, p1: ArrayList<Int>?) {
    }

    override fun stkEventNotify(p0: Int, p1: String?) {
    }

    override fun onSimMissing(p0: Int, p1: Int) {
    }

    override fun onLteAccessStratumStateChanged(p0: Int, p1: ArrayList<Int>?) {
    }

    override fun cdmaCallWaiting(p0: Int, p1: CdmaCallWaiting?) {
    }

    override fun networkScanResult_1_2(p0: Int, p1: android.hardware.radio.V1_2.NetworkScanResult?) {
    }

    override fun indicateRingbackTone(p0: Int, p1: Boolean) {
    }

    override fun cdmaSubscriptionSourceChanged(p0: Int, p1: Int) {
    }

    override fun onSimTrayPlugIn(p0: Int) {
    }

    override fun smlSlotLockInfoChangedInd(p0: Int, p1: ArrayList<Int>?) {
    }

    override fun cfuStatusNotify(p0: Int, p1: CfuStatusNotification?) {
    }

    override fun onSimPlugIn(p0: Int) {
    }

    override fun cdmaInfoRec(p0: Int, p1: CdmaInformationRecords?) {
    }

    override fun esnMeidChangeInd(p0: Int, p1: String?) {
    }

    override fun responseInvalidSimInd(p0: Int, p1: ArrayList<String>?) {
    }

    override fun gmssRatChangedIndication(p0: Int, p1: ArrayList<Int>?) {
    }

    override fun onMdDataRetryCountReset(p0: Int) {
    }

    override fun cdmaCallAccepted(p0: Int) {
    }

    override fun onImeiLock(p0: Int) {
    }

    override fun newSmsOnSim(p0: Int, p1: Int) {
    }

    override fun nitzTimeReceived(p0: Int, p1: String?, p2: Long) {
    }

    override fun newEtwsInd(p0: Int, p1: EtwsNotification?) {
    }

    override fun eMBMSAtInfoIndication(p0: Int, p1: String?) {
    }

    override fun dedicatedBearerDeactivationInd(p0: Int, p1: Int) {
    }

    override fun onUssd(p0: Int, p1: Int, p2: String?) {
    }

    override fun bipProactiveCommand(p0: Int, p1: String?) {
    }

    override fun networkInfoInd(p0: Int, p1: ArrayList<String>?) {
    }

    override fun onTxPowerIndication(p0: Int, p1: ArrayList<Int>?) {
    }

    override fun simStatusChanged(p0: Int) {
    }

    override fun stkProactiveCommand(p0: Int, p1: String?) {
    }

    override fun responseCsNetworkStateChangeInd(p0: Int, p1: ArrayList<String>?) {
    }

    override fun enterEmergencyCallbackMode(p0: Int) {
    }

    override fun cdmaRuimSmsStorageFull(p0: Int) {
    }

    override fun cellInfoList(p0: Int, p1: ArrayList<android.hardware.radio.V1_0.CellInfo>?) {
    }

    override fun crssIndication(p0: Int, p1: CrssNotification?) {
    }

    override fun currentPhysicalChannelConfigs(p0: Int, p1: ArrayList<PhysicalChannelConfig>?) {
    }

    override fun responseFemtocellInfo(p0: Int, p1: ArrayList<String>?) {
    }

    override fun cdmaNewSms(p0: Int, p1: CdmaSmsMessage?) {
    }

    override fun modemReset(p0: Int, p1: String?) {
    }

    override fun radioStateChanged(p0: Int, p1: Int) {
    }

    override fun srvccStateNotify(p0: Int, p1: Int) {
    }

    override fun currentSignalStrengthWithWcdmaEcioInd(p0: Int, p1: SignalStrengthWithWcdmaEcio?) {
    }

    override fun meSmsStorageFullInd(p0: Int) {
    }

    override fun dataAllowedNotification(p0: Int, p1: Int) {
    }

    override fun voiceRadioTechChanged(p0: Int, p1: Int) {
    }

    override fun onVirtualSimOff(p0: Int, p1: Int) {
    }

    override fun resendIncallMute(p0: Int) {
    }

    override fun cellInfoList_1_2(p0: Int, p1: ArrayList<CellInfo>?) {
    }

    override fun onVsimEventIndication(p0: Int, p1: VsimOperationEvent?) {
    }

    override fun oemHookRaw(p0: Int, p1: ArrayList<Byte>?) {
    }

    override fun callRing(p0: Int, p1: Boolean, p2: CdmaSignalInfoRecord?) {
    }

    override fun hardwareConfigChanged(p0: Int, p1: ArrayList<HardwareConfig>?) {
    }

    override fun pcoData(p0: Int, p1: PcoDataInfo?) {
    }

    override fun currentLinkCapacityEstimate(p0: Int, p1: LinkCapacityEstimate?) {
    }

    override fun responseNetworkEventInd(p0: Int, p1: ArrayList<Int>?) {
    }

    override fun eccNumIndication(p0: Int, p1: String?, p2: String?) {
    }

    override fun stkSessionEnd(p0: Int) {
    }

    override fun dataCallListChanged(p0: Int, p1: ArrayList<SetupDataCallResult>?) {
    }

    override fun onPseudoCellInfoInd(p0: Int, p1: ArrayList<Int>?) {
    }
}