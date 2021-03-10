package com.test.kakaobooksearch.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bind:imageUrl")
fun ImageView.setImageUrl(url: String?) {
    url.apply {
        Glide.with(context).load(url).into(this@setImageUrl)
    }
}