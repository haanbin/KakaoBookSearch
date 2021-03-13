package com.test.kakaobooksearch.ext

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.lang.Exception
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("bind:timeStampFormat")
fun TextView.setTimeStampFormat(timeStamp: String) {
    text = try {
        val sdf1 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.KOREA)
        val sdf2 = SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA)
        val date = sdf1.parse(timeStamp)
        date?.let {
            sdf2.format(it)
        }
    } catch (e: Exception) {
        "알 수 없음"
    }
}

@BindingAdapter("bind:usFormat")
fun TextView.setUsFormat(price: String) {
    if (price == "0") {
        text = "-"
        return
    }
    text = try {
        NumberFormat.getInstance(Locale.US).format(price.toLong()) + "원"
    } catch (e: Exception) {
        "-"
    }

}