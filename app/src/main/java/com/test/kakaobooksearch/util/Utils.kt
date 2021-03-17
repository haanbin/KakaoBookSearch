package com.test.kakaobooksearch.util

import java.text.SimpleDateFormat
import java.util.*

fun getSystemTimeToDateFormat(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA)
    val date = Date(System.currentTimeMillis())
    return sdf.format(date)
}