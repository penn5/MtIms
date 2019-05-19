package com.mediatek.ims

import vendor.mediatek.hardware.imsa.V1_0.IImsa
import java.nio.ByteBuffer
import kotlin.experimental.and


object ImsaAdapter {
    // Inspired not copied.
    class VaEvent(var slotId: Int, val requestID: Int, length: Int = DEFAULT_MAX_DATA_LENGTH) {

        var dataLen: Int = 0
            private set
        private var read_offset: Int = 0
        val data: ByteArray
        private var event_max_data_len = DEFAULT_MAX_DATA_LENGTH

        val int: Int
            get() {
                var ret: Int
                synchronized(this) {
                    ret = data[read_offset + 3].and(0xff.toByte()).toInt().toBigInteger().shl(24).or(
                        data[read_offset + 2].and(0xff.toByte()).toInt().toBigInteger().shl(16).or(
                            data[read_offset + 1].and(0xff.toByte()).toInt().toBigInteger().shl(8).or(
                                data[read_offset].and(
                                    0xff.toByte()
                                ).toInt().toBigInteger()
                            )
                        )
                    ).toInt()
                    read_offset += 4
                }
                return ret
            }

        val short: Int
            get() {
                var ret: Int
                synchronized(this) {
                    ret = data[read_offset + 1].and(0xff.toByte()).toInt().toBigInteger().shl(8)
                        .or(data[read_offset].and(0xff.toByte()).toInt().toBigInteger()).toInt()
                    read_offset += 2
                }
                return ret
            }

        // Notice: getByte is to get int8 type from VA, not get one byte.
        val byte: Int
            get() {
                var ret: Int
                synchronized(this) {
                    ret = data[read_offset].and(0xff.toByte()).toInt()
                    read_offset += 1
                }
                return ret
            }

        val double: Double
            get() {
                val buf = ByteArray(8)

                buf[0] = data[read_offset + 7]
                buf[1] = data[read_offset + 6]
                buf[2] = data[read_offset + 5]
                buf[3] = data[read_offset + 4]

                buf[4] = data[read_offset + 3]
                buf[5] = data[read_offset + 2]
                buf[6] = data[read_offset + 1]
                buf[7] = data[read_offset]

                read_offset += 8
                return ByteBuffer.wrap(buf).double
            }

        init {
            this.slotId = slotId
            event_max_data_len = length
            data = ByteArray(event_max_data_len)
            dataLen = 0
            read_offset = 0
        }

        fun putInt(value: Int): Int {
            if (dataLen > event_max_data_len - 4) {
                return -1
            }

            synchronized(this) {
                for (i in 0..3) {
                    data[dataLen] = (value shr 8 * i and 0xFF).toByte()
                    dataLen++
                }
            }
            return 0
        }

        fun putShort(value: Int): Int {
            if (dataLen > event_max_data_len - 2) {
                return -1
            }

            synchronized(this) {
                for (i in 0..1) {
                    data[dataLen] = (value shr 8 * i and 0xFF).toByte()
                    dataLen++
                }
            }

            return 0
        }

        fun putByte(value: Int): Int {
            if (dataLen > event_max_data_len - 1) {
                return -1
            }

            synchronized(this) {
                data[dataLen] = (value and 0xFF).toByte()
                dataLen++
            }

            return 0
        }

        fun putString(str: String, len: Int): Int {
            if (dataLen > event_max_data_len - len) {
                return -1
            }

            synchronized(this) {
                val s = str.toByteArray()
                if (len < str.length) {
                    System.arraycopy(s, 0, data, dataLen, len)
                    dataLen += len
                } else {
                    val remain = len - str.length
                    System.arraycopy(s, 0, data, dataLen, str.length)
                    dataLen += str.length
                    for (i in 0 until remain) {
                        data[dataLen] = 0
                        dataLen++
                    }
                }
            }

            return 0
        }

        fun putBytes(value: ByteArray): Int {
            val len = value.size

            if (len > event_max_data_len) {
                return -1
            }

            synchronized(this) {
                System.arraycopy(value, 0, data, dataLen, len)
                dataLen += len
            }

            return 0
        }

        fun putDouble(value: Double): Int {
            if (dataLen > event_max_data_len - 8) {
                return -1
            }

            val buf = ByteBuffer.allocate(8)
            buf.putDouble(value)

            synchronized(this) {
                data[dataLen++] = buf.get(7)
                data[dataLen++] = buf.get(6)
                data[dataLen++] = buf.get(5)
                data[dataLen++] = buf.get(4)

                data[dataLen++] = buf.get(3)
                data[dataLen++] = buf.get(2)
                data[dataLen++] = buf.get(1)
                data[dataLen++] = buf.get(0)
            }
            return 0
        }

        fun putLong(value: Long): Int {
            if (dataLen > event_max_data_len - 8) {
                return -1
            }

            val buf = ByteBuffer.allocate(8)
            buf.putLong(value)

            synchronized(this) {
                data[dataLen++] = buf.get(7)
                data[dataLen++] = buf.get(6)
                data[dataLen++] = buf.get(5)
                data[dataLen++] = buf.get(4)

                data[dataLen++] = buf.get(3)
                data[dataLen++] = buf.get(2)
                data[dataLen++] = buf.get(1)
                data[dataLen++] = buf.get(0)
            }
            return 0
        }

        fun getBytes(length: Int): ByteArray? {
            if (length > dataLen - read_offset) {
                return null
            }

            val ret = ByteArray(length)

            synchronized(this) {
                for (i in 0 until length) {
                    ret[i] = data[read_offset]
                    read_offset++
                }
                return ret
            }
        }

        fun getString(len: Int): String {
            val buf = ByteArray(len)

            synchronized(this) {
                System.arraycopy(data, read_offset, buf, 0, len)
                read_offset += len
            }

            return String(buf).trim { it <= ' ' }
        }

        companion object {
            val DEFAULT_MAX_DATA_LENGTH = 40960
            val MSG_ID_IMS_ENABLE_IND = 900003
            val MSG_ID_IMS_DISABLE_IND = 900004
        }
    }

    fun enableIms(slotId: Int) {
        val vaEvent = VaEvent(slotId, VaEvent.MSG_ID_IMS_ENABLE_IND)
        getImsa(slotId).writeEvent(vaEvent.requestID, vaEvent.dataLen, ArrayList<Byte>(vaEvent.data.toMutableList()))
    }

    fun disableIms(slotId: Int) {
        val vaEvent = VaEvent(slotId, VaEvent.MSG_ID_IMS_DISABLE_IND)
        getImsa(slotId).writeEvent(vaEvent.requestID, vaEvent.dataLen, ArrayList<Byte>(vaEvent.data.toMutableList()))
    }


    var imsa: IImsa? = null
    fun getImsa(slotId: Int): IImsa {
        return imsa ?: {
            imsa = IImsa.getService("imsa"); imsa!!.setResponseFunctions(MtImsaIndication(slotId)); imsa!!
        }()
    }
}