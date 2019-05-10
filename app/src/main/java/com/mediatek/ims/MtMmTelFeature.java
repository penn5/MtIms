package com.mediatek.ims;

import android.os.RemoteException;
import android.telephony.ims.ImsReasonInfo;
import android.telephony.ims.feature.CapabilityChangeRequest;
import android.telephony.ims.feature.MmTelFeature;
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
        try {
            RilHolder.INSTANCE.getRadio(mSlotId).setImsEnable(RilHolder.INSTANCE.callback((resp1, unused1) -> {
                if (resp1.error != 0)
                    Log.e(LOG_TAG, "Failed to initialize IMS, see earlier logs from RilHolder for error code");
                try {
                    RilHolder.INSTANCE.getRadio(mSlotId).setImscfg(RilHolder.INSTANCE.callback((resp2, unused2) -> {
                                if (resp2.error != 0)
                                    Log.e(LOG_TAG, "Failed to setImscfg, see earlier logs from RilHolder for error code");
                                return null;
                            }, mSlotId), /* VoLTE */ true, /* ViLTE */ false,
                            /* VoWiFi */ false, /* ViWiFi */ false,
                            /* SMS */ false, /* "eims" */ false);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
                return null;
            }, mSlotId), true);

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void deregisterIms() {
        try {
            RilHolder.INSTANCE.getRadio(mSlotId).setImsEnable(RilHolder.INSTANCE.callback((resp1, unused1) -> {
                if (resp1.error != 0)
                    Log.e(LOG_TAG, "Failed to deinitialize IMS, see earlier logs from RilHolder for error code");
                MtImsService.Companion.getInstance().getRegistration(mSlotId).onDeregistered(new ImsReasonInfo());
                try {
                    RilHolder.INSTANCE.getRadio(mSlotId).setImscfg(RilHolder.INSTANCE.callback((resp2, unused2) -> {
                                if (resp2.error != 0)
                                    Log.e(LOG_TAG, "Failed to setImscfg, see earlier logs from RilHolder for error code");
                                return null;
                            }, mSlotId), /* VoLTE */ false, /* ViLTE */ false,
                            /* VoWiFi */false, /* ViWiFi */ false,
                            /* SMS */ false, /* "eims" */ false);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
                return null;
            }, mSlotId), false);

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}