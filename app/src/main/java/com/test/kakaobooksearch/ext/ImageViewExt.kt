package com.test.kakaobooksearch.ext

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.test.kakaobooksearch.R

@BindingAdapter("bind:imageUrl")
fun ImageView.setImageUrl(url: String?) {
    url?.let {
        if (it.isNotEmpty()) {
            Glide.with(context)
                .load(url)
                .into(this@setImageUrl)
        } else {
            setImageDrawable(null)
        }
    }
}