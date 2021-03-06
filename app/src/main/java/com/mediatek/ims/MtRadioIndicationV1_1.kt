package com.mediatek.ims

import android.hardware.radio.V1_0.*
import vendor.mediatek.hardware.radio.V1_1.*
import java.util.*

class MtRadioIndicationV1_1(val mSlotId: Int) : vendor.mediatek.hardware.radio.V1_1.IRadioIndication.Stub() {
    override fun suppSvcNotify(p0: Int, p1: SuppSvcNotification?) {
    }

    override fun simRefresh(p0: Int, p1: SimRefreshResult?) {
    }

    override fun currentSignalStrength(p0: Int, p1: SignalStrength?) {
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

    override fun responseModulationInfoInd(p0: Int, p1: ArrayList<Int>?) {
    }

    override fun restrictedStateChanged(p0: Int, p1: Int) {
    }

    override fun radioCapabilityIndication(p0: Int, p1: RadioCapability?) {
    }

    override fun dataCallListChangedEx(p0: Int, p1: ArrayList<MtkSetupDataCallResult>?) {
    }

    override fun stkCallControlAlphaNotify(p0: Int, p1: String?) {
    }

    override fun newBroadcastSms(p0: Int, p1: ArrayList<Byte>?) {
    }

    override fun registrationSuspendedIndication(p0: Int, p1: ArrayList<Int>?) {
    }

    override fun triggerOtaSP(p0: Int) {
    }

    override fun vtStatusInfoIndication(p0: Int, p1: Int) {
    }

    override fun onPcoStatus(p0: Int, p1: ArrayList<Int>?) {
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

    override fun indicateRingbackTone(p0: Int, p1: Boolean) {
    }

    override fun cdmaSubscriptionSourceChanged(p0: Int, p1: Int) {
    }

    override fun onSimTrayPlugIn(p0: Int) {
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

    override fun cellInfoList(p0: Int, p1: ArrayList<CellInfo>?) {
    }

    override fun crssIndication(p0: Int, p1: CrssNotification?) {
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

    override fun onVsimEventIndication(p0: Int, p1: VsimOperationEvent?) {
    }

    override fun callRing(p0: Int, p1: Boolean, p2: CdmaSignalInfoRecord?) {
    }

    override fun hardwareConfigChanged(p0: Int, p1: ArrayList<HardwareConfig>?) {
    }

    override fun pcoData(p0: Int, p1: PcoDataInfo?) {
    }

    override fun responseNetworkEventInd(p0: Int, p1: ArrayList<Int>?) {
    }

    override fun stkSessionEnd(p0: Int) {
    }

    override fun dataCallListChanged(p0: Int, p1: ArrayList<SetupDataCallResult>?) {
    }

    override fun onPseudoCellInfoInd(p0: Int, p1: ArrayList<Int>?) {
    }

    override fun newSmsStatusReport(p0: Int, p1: ArrayList<Byte>?) {
    }
}