package com.mediatek.ims

import vendor.mediatek.hardware.imsa.V1_0.IImsaIndication
import java.util.*

class MtImsaIndication(slotId: Int) : IImsaIndication.Stub() {
    override fun readEvent(p0: ArrayList<Byte>?, p1: Int, p2: Int) {
        // MEH TODO
    }
}
