package com.mediatek.ims

import android.hardware.radio.V1_0.*
import android.hardware.radio.V1_1.KeepaliveStatus
import android.hardware.radio.V1_1.NetworkScanResult
import android.telephony.Rlog
import android.telephony.ims.ImsReasonInfo
import android.telephony.ims.stub.ImsRegistrationImplBase
import vendor.mediatek.hardware.radio.V2_0.IImsRadioIndication
import vendor.mediatek.hardware.radio.V2_0.IncomingCallNotification

class MtImsRadioIndicationV2_0(private val mSlotId: Int) : IImsRadioIndication.Stub() {
    override fun carrierInfoForImsiEncryption(p0: Int) {
        Rlog.v(tag, "carrierInfoForImsiEncryption($p0)")
    }

    override fun keepaliveStatus(p0: Int, p1: KeepaliveStatus?) {
        Rlog.v(tag, "keepaliveStatus($p0, $p1)")
    }

    override fun multiImsCount(p0: Int, p1: Int) {
        Rlog.v(tag, "multiImsCount($p0, $p1)")
    }

    override fun networkScanResult(p0: Int, p1: NetworkScanResult?) {
        Rlog.v(tag, "networkScanResult($p0, $p1)")
    }

    override fun imsBearerDeactivation(p0: Int, p1: Int, p2: String?) {
        Rlog.v(tag, "imsBearerDeactivation($p0, $p1, $p2)")
    }

    override fun imsEventPackageIndication(p0: Int, p1: String?, p2: String?, p3: String?, p4: String?, p5: String?) {
        Rlog.v(tag, "imsEventPackageIndication($p0, $p1, $p2, $p3, $p4, $p5)")
    }

    override fun newSmsStatusReport(p0: Int, p1: ArrayList<Byte>?) {
        Rlog.v(tag, "newSmsStatusReport($p0, $p1)")
    }

    override fun imsDisableStart(p0: Int) {
        Rlog.v(tag, "imsDisableStart($p0)")
    }

    override fun onUssi(
        p0: Int,
        p1: String?,
        p2: String?,
        p3: String?,
        p4: String?,
        p5: String?,
        p6: String?,
        p7: String?
    ) {
        Rlog.v(tag, "onUssi($p0, $p1, $p2, $p3, $p4, $p5, $p6, $p7)")
    }

    @Suppress("UNCHECKED_CAST") // Kotlin doesn't support checking the class in the list, so it raises a warning
    // but we don't care because it is checked by us.
    override fun callStateChanged(p0: Int) {
        Rlog.v(tag, "callStateChanged($p0)")
        RilHolder.getRadio(mSlotId).getCurrentCalls(RilHolder.getNextSerial())
    }

    override fun suppSvcNotify(p0: Int, p1: SuppSvcNotification?) {
        Rlog.v(tag, "suppSvcNotify($p0, $p1)")
    }

    override fun ectIndication(p0: Int, p1: Int, p2: Int, p3: Int) {
        Rlog.v(tag, "ectIndication($p0, $p1, $p2, $p3)")
    }

    override fun simRefresh(p0: Int, p1: SimRefreshResult?) {
        Rlog.v(tag, "simRefresh($p0, $p1)")
    }

    override fun imsRegistrationInfo(type: Int, status: Int, capability: Int) {
        Rlog.v(tag, "imsRegistrationInfo(type, $status, $capability)")
        //TODO
    }

    override fun currentSignalStrength(p0: Int, p1: SignalStrength?) {
        Rlog.v(tag, "currentSignalStrength($p0, $p1)")
    }

    override fun imsBearerInit(p0: Int) {
        Rlog.v(tag, "imsBearerInit($p0)")
        //TODO
    }

    override fun stkEventNotify(p0: Int, p1: String?) {
        Rlog.v(tag, "stkEventNotify($p0, $p1)")
    }

    override fun cdmaOtaProvisionStatus(p0: Int, p1: Int) {
        Rlog.v(tag, "cdmaOtaProvisionStatus($p0)")
    }

    override fun cdmaCallWaiting(p0: Int, p1: CdmaCallWaiting?) {
        Rlog.v(tag, "cdmaCallWaiting($p0, $p1)")
    }

    override fun indicateRingbackTone(p0: Int, p1: Boolean) {
        Rlog.v(tag, "indicateRingbackTone($p0, $p1)")
    }

    override fun cdmaSubscriptionSourceChanged(p0: Int, p1: Int) {
        Rlog.v(tag, "cdmaSubscriptionSourceChanged($p0, $p1)")
    }

    override fun simSmsStorageFull(p0: Int) {
        Rlog.v(tag, "simsSmsStorageFull($p0)")
    }

    override fun imsSupportEcc(p0: Int, p1: Int) {
        Rlog.v(tag, "imsSupportEcc($p0, $p1)")
    }

    override fun cdmaInfoRec(type: Int, infos: CdmaInformationRecords?) {
        Rlog.v(tag, "cdmaInfoRec($type, $infos)")
    }

    override fun lceData(type: Int, infos: LceDataInfo?) {
        Rlog.v(tag, "lceData($type, $infos)")
    }

    override fun imsRtpInfo(p0: Int, p1: String?, p2: String?, p3: String?, p4: String?, p5: String?) {
        Rlog.v(tag, "imsRtpInfo($p0, $p1, $p2, $p3, $p4, $p5)")
    }

    override fun econfResultIndication(
        p0: Int,
        p1: String?,
        p2: String?,
        p3: String?,
        p4: String?,
        p5: String?,
        p6: String?
    ) {
        Rlog.v(tag, "econfResultIndication($p0, $p1, $p2, $p3, $p4, $p5, $p6)")
    }

    override fun callInfoIndication(type: Int, data: ArrayList<String>?) {
        Rlog.v(tag, "callInfoIndication($type, $data)") //TODO PII
        RilHolder.getRadio(mSlotId).getCurrentCalls(RilHolder.getNextSerial())
        // This is gotten from Mtk ImsService, but written by me
        data?.let {
            if (data.size > 0) {
                val callId = data[0]
                val msgType = data[1].toInt()
                val callNumber = if (data.size < 7) "" else data[6]
                val callMode = if (data[5] == "") 0xFF
                else
                    data[5].toInt()
                // p1=msgType, p2=callMode, p3=callId, p4=callNumber

                val conference = when (callMode) {
                    24 -> true
                    25 -> true
                    else -> false
                }

                Rlog.d(
                    tag,
                    "callInfoIndication, callId=$callId, msgType=$msgType, callNumber=${Rlog.pii(
                        tag,
                        callNumber
                    )}, callMode=$callMode, conference=$conference"
                )

                when (msgType) {
                    0 -> {
                        Rlog.d(tag, "New INCOMING")
                    } // Create new incoming call
                    2 -> {
                        Rlog.d(tag, "Conference to $conference")
                    } // Change conference mode
                    130 -> {
                        Rlog.d(tag, "New ALERTING")
                    } // Create new alerting call
                    131 -> {
                        Rlog.d(tag, "Enter HOLDING and conference $conference")
                    } // Enter holding mode and change conference mode
                    132 -> {
                        Rlog.d(tag, "Enter ACTIVE and conference $conference")
                    } // Enter active mode and change conference mode
                    133 -> {
                        Rlog.d(tag, "Call TERMINATE")
                    } // Call ended; delete
                }
            }
        }
    }

    override fun newSmsOnSim(p0: Int, p1: Int) {
        Rlog.v(tag, "newSmsOnSim($p0, $p1)")
    }

    override fun nitzTimeReceived(p0: Int, p1: String?, p2: Long) {
        Rlog.v(tag, "nitzTimeReceived($p0, $p1, $p2)")
    }

    override fun subscriptionStatusChanged(p0: Int, p1: Boolean) {
        Rlog.v(tag, "subscriptionStatusChanged($p0, $p1)")
    }

    override fun exitEmergencyCallbackMode(p0: Int) {
        Rlog.v(tag, "exitECBM($p0)")
    }

    override fun imsNetworkStateChanged(p0: Int) {
        Rlog.v(tag, "imsNetworkStateChanged($p0)")
        //TODO
    }

    override fun onUssd(p0: Int, p1: Int, p2: String?) {
        Rlog.v(tag, "onUssd($p0, $p1, $p2)")
    }

    override fun incomingCallIndication(type: Int, call: IncomingCallNotification?) {
        Rlog.d(tag, "incomingCallIndication($type, $call)") // TODO PII
        RilHolder.getRadio(mSlotId).getCurrentCalls(RilHolder.getNextSerial())
    }

    override fun getProvisionDone(p0: Int, p1: String?, p2: String?) {
        Rlog.v(tag, "getProvisionDone($p0, $p1, $p2)")
    }

    override fun simStatusChanged(p0: Int) {
        Rlog.v(tag, "simStatusChanged($p0)")
    }

    override fun callmodChangeIndicator(p0: Int, p1: String?, p2: String?, p3: String?, p4: String?, p5: String?) {
        Rlog.v(tag, "callmodChangeIndicator($p0, $p1, $p2, $p3, $p4, $p5)")
    }

    override fun stkProactiveCommand(p0: Int, p1: String?) {
        Rlog.v(tag, "sktProactiveCommand($p0, $p1)")
    }

    override fun imsDeregDone(p0: Int) {
        Rlog.d(tag, "imsDeregDone($p0)")
        MtImsService.instance!!.getRegistration(mSlotId).notifyDeregistered(ImsReasonInfo())
    }

    override fun stkCallSetup(p0: Int, p1: Long) {
        Rlog.v(tag, "stkCallSetup($p0, $p1)")
    }

    override fun rilConnected(p0: Int) {
        Rlog.d(tag, "rilConnected($p0)")
        //TODO
    }

    override fun imsEnableStart(p0: Int) {
        Rlog.d(tag, "imsEnableStart($p0)")
        MtImsService.instance!!.getRegistration(mSlotId).notifyRegistered(ImsRegistrationImplBase.REGISTRATION_TECH_LTE)
        MtImsService.instance!!.createMmTelFeature(mSlotId).setVoiceRegistered(true)
    }

    override fun enterEmergencyCallbackMode(p0: Int) {
        Rlog.v(tag, "enterECBM($p0)")
    }

    override fun cdmaRuimSmsStorageFull(p0: Int) {
        Rlog.v(tag, "cdmaRuimSmsStorageFull($p0)")
    }

    override fun videoCapabilityIndicator(p0: Int, p1: String?, p2: String?, p3: String?) {
        Rlog.v(tag, "videoCapabilityIndicator($p0, $p1, $p2, $p3)")
    }

    override fun cellInfoList(p0: Int, p1: ArrayList<CellInfo>?) {
        Rlog.v(tag, "cellInfoList($p0, $p1)")
    }

    override fun onXui(p0: Int, p1: String?, p2: String?, p3: String?) {
        Rlog.v(tag, "onXui($p0, $p1, $p2, $p3)")
    }

    override fun cdmaNewSms(p0: Int, p1: CdmaSmsMessage?) {
        Rlog.v(tag, "cdmaNewSms($p0, $p1)")
    }

    override fun restrictedStateChanged(p0: Int, p1: Int) {
        Rlog.v(tag, "restrictedStateChanged($p0, $p1)")
    }

    override fun modemReset(p0: Int, p1: String?) {
        Rlog.v(tag, "modemReset($p0, $p1)")
        //TODO die?
    }

    override fun radioStateChanged(p0: Int, p1: Int) {
        Rlog.v(tag, "radioStateChanged($p0, $p1)")
    }

    override fun radioCapabilityIndication(p0: Int, p1: RadioCapability?) {
        Rlog.d(tag, "radioCapabilityIndication($p0, $p1)")
        //TODO?
    }

    override fun srvccStateNotify(p0: Int, p1: Int) {
        Rlog.v(tag, "srvccStateNotify($p0, $p1)")
    }

    override fun volteSetting(p0: Int, p1: Boolean) {
        Rlog.e(tag, "volteSetting($p0, $p1)")
        if (p1)
            MtImsService.instance!!.getRegistration(mSlotId).notifyRegistered(ImsRegistrationImplBase.REGISTRATION_TECH_LTE)
    }

    override fun stkCallControlAlphaNotify(p0: Int, p1: String?) {
        Rlog.v(tag, "stkCallControlAlphaNotify($p0, $p1)")
    }

    override fun newBroadcastSms(p0: Int, p1: ArrayList<Byte>?) {
        Rlog.v(tag, "newBroadcastSms($p0, $p1)")
    }

    override fun voiceRadioTechChanged(p0: Int, p1: Int) {
        Rlog.d(tag, "voiceRadioTechChanged($p0, $p1)")
        //TODO
    }

    override fun imsEnableDone(p0: Int) {
        Rlog.d(tag, "imsEnableDone($p0)")
        MtImsService.instance!!.getRegistration(mSlotId).notifyRegistered(ImsRegistrationImplBase.REGISTRATION_TECH_NONE)
        MtImsService.instance!!.createMmTelFeature(mSlotId).setVoiceRegistered(true)
    }

    override fun imsBearerActivation(type: Int, aid: Int, capability: String?) {
        Rlog.d(tag, "imsBearerActivation($type, $aid, $capability)")
        // We don't need this. I hope.
    }

    override fun resendIncallMute(p0: Int) {
        Rlog.v(tag, "resendIncallMute($p0)")
    }

    override fun networkStateChanged(p0: Int) {
        Rlog.v(tag, "networkStateChanged($p0)")
    }

    override fun onSupplementaryServiceIndication(p0: Int, p1: StkCcUnsolSsResult?) {
        Rlog.v(tag, "onSupplementaryServiceIndication($p0, $p1)")
    }

    override fun imsDisableDone(p0: Int) {
        Rlog.d(tag, "imsDisableDone($p0)")
        MtImsService.instance!!.getRegistration(mSlotId).notifyDeregistered(ImsReasonInfo())
    }

    override fun sipCallProgressIndicator(
        p0: Int,
        p1: String?,
        p2: String?,
        p3: String?,
        p4: String?,
        p5: String?,
        p6: String?
    ) {
        Rlog.v(tag, "sipCallProgressIndicator($p0, $p1, $p2, $p3, $p4, $p5, $p6)")
        //TODO?
    }

    override fun callRing(p0: Int, p1: Boolean, p2: CdmaSignalInfoRecord?) {
        Rlog.d(tag, "callRing($p0, $p1, $p2)")
    }

    override fun hardwareConfigChanged(p0: Int, p1: ArrayList<HardwareConfig>?) {
        Rlog.v(tag, "hardwareConfigChanged($p0, $p1)")
    }

    override fun pcoData(p0: Int, p1: PcoDataInfo?) {
        Rlog.v(tag, "pcoData($p0, $p1)")
    }

    override fun newSms(p0: Int, p1: ArrayList<Byte>?) {
        Rlog.d(tag, "newSms($p0, $p1)")
    }

    override fun stkSessionEnd(p0: Int) {
        Rlog.v(tag, "stkSessionEnd($p0)")
    }

    override fun cdmaPrlChanged(p0: Int, p1: Int) {
        Rlog.v(tag, "cdmaPrlChanged($p0, $p1)")
    }

    override fun dataCallListChanged(p0: Int, p1: ArrayList<SetupDataCallResult>?) {
        Rlog.w(tag, "dataCallListChanged($p0, $p1)")
        //TODO?
    }

    companion object {
        const val tag = "MtImsRadioIndicationV1_1"
    }

}
