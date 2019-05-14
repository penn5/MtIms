package com.mediatek.ims;

import android.annotation.NonNull;
import android.telephony.Rlog;
import android.telephony.ims.ImsCallProfile;
import android.telephony.ims.ImsReasonInfo;
import android.telephony.ims.feature.CapabilityChangeRequest;
import android.telephony.ims.feature.MmTelFeature;
import android.telephony.ims.stub.ImsCallSessionImplBase;
import android.telephony.ims.stub.ImsRegistrationImplBase;
import android.util.Log;
import android.util.SparseArray;

public class MtMmTelFeature extends MmTelFeature {
    private final String LOG_TAG = "MtImsMmTelFeatureImpl";
    // Enabled Capabilities - not status
    private final SparseArray<MmTelCapabilities> mEnabledCapabilities = new SparseArray<>();
    private final int mSlotId;

    public MtMmTelFeature(int slotId) {
        mSlotId = slotId;
        mEnabledCapabilities.append(ImsRegistrationImplBase.REGISTRATION_TECH_LTE,
                new MmTelCapabilities(MmTelCapabilities.CAPABILITY_TYPE_VOICE));
        mEnabledCapabilities.append(ImsRegistrationImplBase.REGISTRATION_TECH_LTE,
                new MmTelCapabilities(0));
        setFeatureState(STATE_READY);
    }

    @Override
    public boolean queryCapabilityConfiguration(int capability, int radioTech) {
        return mEnabledCapabilities.get(radioTech).isCapable(capability);
    }

    @Override
    public void changeEnabledCapabilities(CapabilityChangeRequest request,
                                          CapabilityCallbackProxy c) {
        for (CapabilityChangeRequest.CapabilityPair pair : request.getCapabilitiesToEnable()) {
            mEnabledCapabilities.get(pair.getRadioTech()).addCapabilities(pair.getCapability());
        }
        for (CapabilityChangeRequest.CapabilityPair pair : request.getCapabilitiesToDisable()) {
            mEnabledCapabilities.get(pair.getRadioTech()).removeCapabilities(pair.getCapability());
        }
    }

    public void registerIms() {
        MtImsService.Companion.getInstance().getRegistration(mSlotId).onRegistering(ImsRegistrationImplBase.REGISTRATION_TECH_NONE);
        RilHolder.INSTANCE.getRadio(mSlotId).setImsEnable(RilHolder.INSTANCE.callback((resp1, unused1) -> {
            if (resp1.error != 0)
                Log.e(LOG_TAG, "Failed to initialize IMS, see earlier logs from RilHolder for error code");
            else {
                RilHolder.INSTANCE.getRadio(mSlotId).setImscfg(RilHolder.INSTANCE.callback((resp2, unused2) -> {
                            if (resp2.error != 0)
                                Log.e(LOG_TAG, "Failed to setImscfg, see earlier logs from RilHolder for error code");
                            else {
                                Rlog.d(LOG_TAG, "Success to setImscfg, yay");
                                MmTelCapabilities capabilities = new MmTelCapabilities();
                                capabilities.addCapabilities(MmTelCapabilities.CAPABILITY_TYPE_VOICE);
                                notifyCapabilitiesStatusChanged(capabilities);
                                MtImsService.Companion.getInstance().getRegistration(mSlotId).onRegistered(ImsRegistrationImplBase.REGISTRATION_TECH_NONE);
                            }
                            return null;
                        }, mSlotId), /* VoLTE */ true, /* ViLTE */ false,
                        /* VoWiFi */ false, /* ViWiFi */ false,
                        /* SMS */ false, /* "eims" */ true);
            }
            return null;
        }, mSlotId), true);

    }

    public void deregisterIms() {
        RilHolder.INSTANCE.getRadio(mSlotId).setImsEnable(RilHolder.INSTANCE.callback((resp1, unused1) -> {
            if (resp1.error != 0)
                Log.e(LOG_TAG, "Failed to deinitialize IMS, see earlier logs from RilHolder for error code");
            MtImsService.Companion.getInstance().getRegistration(mSlotId).onDeregistered(new ImsReasonInfo());
            RilHolder.INSTANCE.getRadio(mSlotId).setImscfg(RilHolder.INSTANCE.callback((resp2, unused2) -> {
                        if (resp2.error != 0)
                            Log.e(LOG_TAG, "Failed to setImscfg, see earlier logs from RilHolder for error code");
                        else {
                            MmTelCapabilities capabilities = new MmTelCapabilities();
                            capabilities.addCapabilities(0);
                            notifyCapabilitiesStatusChanged(capabilities);
                            MtImsService.Companion.getInstance().getRegistration(mSlotId).onDeregistered(new ImsReasonInfo());
                        }
                        return null;
                    }, mSlotId), /* VoLTE */ false, /* ViLTE */ false,
                    /* VoWiFi */false, /* ViWiFi */ false,
                    /* SMS */ false, /* "eims" */ false);
            return null;
        }, mSlotId), false);

    }


    @Override
    public ImsCallProfile createCallProfile(int callSessionType, int callType) {
        if (callSessionType == ImsCallProfile.SERVICE_TYPE_EMERGENCY) {
            return null;
        }
        if (callSessionType == ImsCallProfile.SERVICE_TYPE_NONE) {
            // Register IMS
            registerIms();
        }
        return new ImsCallProfile(callSessionType, callType);
        // Is this right?
    }

    @Override
    public synchronized ImsCallSessionImplBase createCallSession(@NonNull ImsCallProfile profile) {
        return new MtImsCallSession(mSlotId, profile);
    }

    @Override
    public void onFeatureRemoved() {
        super.onFeatureRemoved();
    }

    @Override
    public void onFeatureReady() {
        super.onFeatureReady();
    }

}
