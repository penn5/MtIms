package com.mediatek.ims

import android.app.Activity
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
}
