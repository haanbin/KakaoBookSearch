package com.test.kakaobooksearch.ext

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.test.kakaobooksearch.R

@BindingAdapter(
    "bind:imageUrl",
    "bind:error"
)
fun ImageView.setImageUrl(url: String?, error: Drawable) {
    url?.let {
        if (it.isNotEmpty()) {
            Glide.with(context)
                .load(url)
                .error(error)
                .into(this@setImageUrl)
        } else {
            setImageDrawable(error)
        }
    }
}