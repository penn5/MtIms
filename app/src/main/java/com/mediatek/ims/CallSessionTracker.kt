package com.mediatek.ims

import java.util.concurrent.ConcurrentHashMap

object CallSessionTracker {
    private val establishedCalls = ConcurrentHashMap<Int, MtImsCallSession>()
    private val MOCalls = ConcurrentHashMap<String, MtImsCallSession>()
    fun getCallByIndex(index: Int): MtImsCallSession? {
        return establishedCalls[index]
    }

    fun giveIndexToMONumber(number: String, index: Int) {
        val call = MOCalls[number] ?: MOCalls["+$number"] ?: MOCalls[number.substring(1)]
        call?.giveIndexFromRIL(index)
    }

    fun addCallWithIndex(index: Int, mtImsCallSession: MtImsCallSession) {
        establishedCalls[index] = mtImsCallSession
    }

    fun addCallWithoutIndex(number: String, mtImsCallSession: MtImsCallSession) {
        MOCalls[number] = mtImsCallSession
    }
}