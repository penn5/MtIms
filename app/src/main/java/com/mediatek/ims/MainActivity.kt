package com.mediatek.ims

import android.annotation.SuppressLint
import android.app.Activity
import android.hardware.radio.V1_0.RadioResponseInfo
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun syncPref(v: View) {
        RilHolder.getRadio(slotid.text.toString().toInt())
            .setVoiceDomainPreference(-1, voltepref.text.toString().toInt())
    }

    @SuppressLint("SetTextI18n")
    fun sendATCommand(v: View) {
        val slotId = slotid.text.toString().toInt()
        RilHolder.getRadio(slotId)
            .sendATCommand(RilHolder.callback({ radioResponseInfo: RadioResponseInfo, data: Array<out Any?> ->
                val time = (System.currentTimeMillis() / 1000).toString()
                runOnUiThread {
                    atout.text = "${atout.text}\n\n$time: AT response $radioResponseInfo:"
                    @Suppress("UNCHECKED_CAST")
                    if (data.isEmpty())
                        atout.text = "${atout.text}\n\tNo data."
                    else {
                        val atLines = data[0] as? ArrayList<String>
                        if (atLines != null) {
                            for (line in atLines) {
                                atout.text = "${atout.text}\n\t$line"
                                atout.invalidate()
                            }
                        } else {
                            atout.text = "${atout.text}\n\tNull or malformed data"
                        }
                    }
                }
            }, slotId), atcmd.text.toString())
    }
}
