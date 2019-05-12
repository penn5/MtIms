package com.mediatek.ims

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.hardware.radio.V1_0.RadioResponseInfo
import android.os.RemoteException
import android.util.Log
import vendor.mediatek.hardware.radio.V1_1.IRadio
import java.util.*
import java.util.concurrent.ConcurrentHashMap

object RilHolder {

    private const val LOG_TAG = "MtImsRilHolder"
    private val serviceNames = arrayOf("imsrild1", "imsrild2", "imsrild3", "imsrild4")
    private val responseCallbacks = arrayOfNulls<MtImsRadioResponse>(3)
    private val unsolCallbacks = arrayOfNulls<MtImsRadioIndication>(3)
    private val radioImpls = arrayOfNulls<IRadio>(3)
    private var nextSerial = -1
    private val serialToSlot = ConcurrentHashMap<Int, Int>()
    private val callbacks = ConcurrentHashMap<Int, (RadioResponseInfo, Array<out Any?>) -> Unit>()
    private val blocks = ConcurrentHashMap<Int, BlockingCallback>()


    @Synchronized
    fun getRadio(slotId: Int): IRadio {
        if (radioImpls[slotId] == null) {
            try {
                try {
                    radioImpls[slotId] = IRadio.getService(serviceNames[slotId])
                } catch (e: NoSuchElementException) {
                    Log.e(LOG_TAG, "Index oob in rilholder. Bail Out!!!", e)
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

                responseCallbacks[slotId] = MtImsRadioResponse(slotId)
                unsolCallbacks[slotId] = MtImsRadioIndication(slotId)
            } catch (e: RemoteException) {
                Log.e(LOG_TAG, "remoteexception getting serivce. will throw npe later ig.")
                throw RuntimeException("Failed to get service due to internal error")
            }

        }
        try {
            radioImpls[slotId]!!.setResponseFunctions(responseCallbacks[slotId], unsolCallbacks[slotId])
            // As we use imsrild, we don't need to set the mtk one to work
        } catch (e: RemoteException) {
            Log.e(LOG_TAG, "Failed to update resp functions!")
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
        Log.v(LOG_TAG, "Setting callback for serial $serial")
        return serial
    }

    @Synchronized
    fun getNextSerial(): Int {
        return ++nextSerial
    }

    fun triggerCB(serial: Int, radioResponseInfo: RadioResponseInfo, vararg args: Any?) {
        Log.e(
            LOG_TAG,
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
