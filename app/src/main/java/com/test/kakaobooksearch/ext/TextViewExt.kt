package com.test.kakaobooksearch.ext

import android.graphics.Paint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("bind:timeStampFormat")
fun TextView.setTimeStampFormat(timeStamp: String) {
    text = try {
        val sdf1 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.KOREA)
        val sdf2 = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA)
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

@BindingAdapter(
    "bind:bookPrice",
    "bind:bookSalePrice"
)
fun TextView.setBooPrice(price: String, salePrice: String) {
    if (price == "0") {
        text = "-"
        return
    }
    if (salePrice != "-1") {
        paintFlags = this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }
    text = try {
        NumberFormat.getInstance(Locale.US).format(price.toLong()) + "원"
    } catch (e: Exception) {
        "-"
    }
}

@BindingAdapter(
    "bind:listToString",
    "bind:listSeparator",
    "bind:listAddString",
    requireAll = false
)
fun TextView.setListToString(list: List<String>, listSeparator: String?, listAddString: String?) {
    val listSeparatorNotNull = listSeparator ?: ", "
    val listAddStringNotNull = listAddString ?: ""
    val formatText = list.joinToString(listSeparatorNotNull)
    text = if (formatText.isEmpty()) {
        ""
    } else {
        "$formatText$listAddStringNotNull"
    }
}
