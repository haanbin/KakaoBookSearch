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
                .error(R.drawable.ic_no_image)
                .into(this@setImageUrl)
        } else {
            setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_no_image))
        }
    }
}