package com.mediatek.ims

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.hardware.radio.V1_0.RadioResponseInfo
import android.os.RemoteException
import android.util.Log
import java.util.concurrent.ConcurrentHashMap

object RilHolder {

    private const val tag = "MtImsRilHolder"
    private val serviceNames = arrayOf("imsrild1", "imsrild2", "imsrild3", "imsrild4")
    private var version: Int = -1
    private val responseCallbacks = arrayOfNulls<android.hardware.radio.V1_0.IRadioResponse>(3)
    private val unsolCallbacks = arrayOfNulls<android.hardware.radio.V1_0.IRadioIndication>(3)
    private val radioImpls = arrayOfNulls<IRadioDelegator>(3)
    private var nextSerial = -1
    private val serialToSlot = ConcurrentHashMap<Int, Int>()
    private val callbacks = ConcurrentHashMap<Int, (RadioResponseInfo, Array<out Any?>) -> Unit>()
    private val blocks = ConcurrentHashMap<Int, BlockingCallback>()


    private fun getIRadio(serviceName: String): IRadioDelegator {
        val iRadioDelegator = IRadioDelegator()
        try {
            iRadioDelegator.setIRadio1(vendor.mediatek.hardware.radio.V1_1.IRadio.getService(serviceName))
            version = 1
        } catch (e: NoSuchElementException) {
            Log.w(tag, "Failed to get IRadio1")
        }
        try {
            iRadioDelegator.setIRadio2(vendor.mediatek.hardware.radio.V2_0.IRadio.getService(serviceName))
            version = 2
        } catch (e: NoSuchElementException) {
            Log.w(tag, "Failed to get IRadio2")
        }
        try {
            iRadioDelegator.setIRadio3(vendor.mediatek.hardware.radio.V3_0.IRadio.getService(serviceName))
            version = 3
        } catch (e: NoSuchElementException) {
            Log.w(tag, "Failed to get IRadio3")
        }
        iRadioDelegator.ensureSet()
        return iRadioDelegator
    }

    @Synchronized
    fun getRadio(slotId: Int): IRadioDelegator {
        if (radioImpls[slotId] == null) {
            try {
                try {
                    radioImpls[slotId] = getIRadio(serviceNames[slotId])
                } catch (e: NoSuchElementException) {
                    Log.e(tag, "Index oob in rilholder. Bail Out!!!", e)
                    val notificationManager = MtImsService.instance!!.getSystemService(NotificationManager::class.java)
                    val channel = NotificationChannel("MtIms", "MtIms", NotificationManager.IMPORTANCE_HIGH)
                    notificationManager.createNotificationChannel(channel)
                    notificationManager.cancelAll()
                    val n = Notification.Builder(MtImsService.instance, "MtIms")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("MtIms not supported")
                        .setContentText("Please uninstall MtIms application from settings ASAP! Caused by broken IRadio or SELinux, try permissive.")
                        .setAutoCancel(true)
                        .build()
                    notificationManager.notify(0, n)
                    android.os.Process.killProcess(android.os.Process.myPid())
                    // We're dead.
                }

                responseCallbacks[slotId] = when (version) {
                    1 -> MtImsRadioResponseV1_1(slotId)
                    2 -> MtImsRadioResponseV2_0(slotId)
                    3 -> MtImsRadioResponseV3_0(slotId)
                    else -> null
                }
                unsolCallbacks[slotId] = when (version) {
                    1 -> MtImsRadioIndicationV1_1(slotId)
                    2 -> MtImsRadioIndicationV2_0(slotId)
                    3 -> MtImsRadioIndicationV3_0(slotId)
                    else -> null
                }
            } catch (e: RemoteException) {
                Log.e(tag, "remoteexception getting serivce. will throw npe later ig.")
                throw RuntimeException("Failed to get service due to internal error")
            }

        }
        try {
            radioImpls[slotId]!!.setResponseFunctions(responseCallbacks[slotId], unsolCallbacks[slotId])
            radioImpls[slotId]!!.setResponseFunctionsIms(responseCallbacks[slotId], unsolCallbacks[slotId])
            // As we use imsrild, we don't need to set the mtk one to work
        } catch (e: RemoteException) {
            Log.e(tag, "Failed to update resp functions!")
        }

        return radioImpls[slotId]!!
    }

    class BlockingCallback {
        private val lock = Object()
        private var done = false
        private var radioResponseInfo: RadioResponseInfo? = null
        private var args: Array<out Any?> = arrayOfNulls(0)

        fun run(radioResponseInfo: RadioResponseInfo, vararg args: Any?) {
            synchronized(lock) {
                if (done)
                    throw RuntimeException("May not call the callback twice for the same serial!")
                this.radioResponseInfo = radioResponseInfo
                this.args = args
                done = true
                lock.notifyAll()
            }
        }

        fun get(): RadioResponseInfo {
            synchronized(lock) {
                while (!done) {
                    lock.wait()
                }
            }
            return radioResponseInfo!!
            // The lock ensures it's never null. An NPE here means something went really wrong.
        }
    }

    @Synchronized
    fun callback(cb: (RadioResponseInfo, Array<out Any?>) -> Unit, slotId: Int): Int {
        val serial = getNextSerial()
        serialToSlot[serial] = slotId
        callbacks[serial] = cb
        Log.v(tag, "Setting callback for serial $serial")
        return serial
    }

    @Synchronized
    fun getNextSerial(): Int {
        return ++nextSerial
    }

    fun triggerCB(serial: Int, radioResponseInfo: RadioResponseInfo, vararg args: Any?) {
        Log.e(
            tag,
            "Incoming response for slot " + serialToSlot[serial] + ", serial " + serial + ", radioResponseInfo " + radioResponseInfo + ", args " + args
        )
        if (callbacks.containsKey(serial))
            callbacks[serial]!!(radioResponseInfo, args)
    }

    fun prepareBlock(slotId: Int): Int {
        val cb = BlockingCallback()
        val serial = callback(cb::run, slotId)
        blocks[serial] = cb
        return serial
    }

    /*
 * It is safe to call this method multiple times, it will always return the same for the same serial.
 */
    fun blockUntilComplete(serial: Int): RadioResponseInfo {
        return blocks[serial]?.get()
            ?: throw RuntimeException("prepareBlock was not called for this request!")

    }

}
