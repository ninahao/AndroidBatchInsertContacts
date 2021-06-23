package com.example.contactbatchinsert

import android.util.Log

object RandomValue {
    const val nameBase = "abcdefghigklmnopqrstuvwxyz"
    val telFirst =
        "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",")
            .toTypedArray()

    private fun getNum(start: Int, end: Int): Int {
        return (Math.random()*(end-start+1)+start).toInt()
    }

    fun getName(lMin: Int = 2, lMax: Int = 5): String {
        Log.i("TAG", "create random name")
        val length = getNum(lMin, lMax)
        val sb = StringBuffer()
        for (i in 0 until length) {
            val index = (Math.random() * nameBase.length).toInt()
            sb.append(nameBase[index])
        }
        return sb.toString()
    }

    fun getTel(): String {
        Log.i("TAG", "create random telephone")
        val index = getNum(0, telFirst.size-1)
        val first = telFirst[index]
        val second = (getNum(1, 888) + 10000).toString().substring(1)
        val third = (getNum(1, 9100) + 10000).toString().substring(1)
        return first+second+third
    }

    fun getUAETel(): String {
        val first = "5"
        val second = getNum(0, 99999999).toString()
        return first+second
    }
}