package com.mediatek.ims

import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import android.telephony.ims.ImsService
import android.telephony.ims.feature.ImsFeature
import android.telephony.ims.stub.ImsConfigImplBase
import android.telephony.ims.stub.ImsFeatureConfiguration
import android.telephony.ims.stub.ImsRegistrationImplBase
import android.util.Log

class MtImsService : ImsService() {
    private var mmTelFeatures = arrayOfNulls<MtMmTelFeature>(3)
    private var registrations = arrayOfNulls<MtImsRegistration>(3)
    private var configs = arrayOfNulls<MtImsConfig>(3)
    internal lateinit var subscriptionManager: SubscriptionManager
    internal lateinit var telephonyManager: TelephonyManager

    override fun onCreate() {
        Log.v(tag, "MtImsService version ${BuildConfig.GIT_HASH} created!")
        subscriptionManager = getSystemService(SubscriptionManager::class.java)
        telephonyManager = getSystemService(TelephonyManager::class.java)
    }

    override fun onDestroy() {
        Log.v(tag, "MtImsService destroyed!")
        instance = null
    }

    override fun readyForFeatureCreation() {
        if (instance != null && instance !== this) {
            throw RuntimeException()
        }
        instance = this
    }

    override fun querySupportedImsFeatures(): ImsFeatureConfiguration {
        return ImsFeatureConfiguration.Builder()
            .addFeature(0, ImsFeature.FEATURE_MMTEL)
            .addFeature(1, ImsFeature.FEATURE_MMTEL)
            .build()
    }

    override fun createMmTelFeature(slotId: Int): MtMmTelFeature {
        if (mmTelFeatures[slotId] == null) {
            mmTelFeatures[slotId] = MtMmTelFeature(slotId)
        }
        return mmTelFeatures[slotId]!!
    }

    override fun getConfig(slotId: Int): ImsConfigImplBase {
        if (configs[slotId] == null) {
            configs[slotId] = MtImsConfig(slotId)
        }
        return configs[slotId]!!
    }

    override fun getRegistration(slotId: Int): ImsRegistrationImplBase {
        if (this.registrations[slotId] == null) {
            registrations[slotId] = MtImsRegistration(slotId)
        }
        return this.registrations[slotId]!!
    }

    override fun enableIms(slotId: Int) {
        createMmTelFeature(slotId).registerIms()
    }

    override fun disableIms(slotId: Int) {
        createMmTelFeature(slotId).deregisterIms()
    }

    companion object {
        var instance: MtImsService? = null
        const val tag = "MtImsService"
    }
}