package com.test.kakaobooksearch.ext

import android.view.View
import androidx.core.view.WindowInsetsCompat

fun View.keyboardIsVisible(): Boolean {
    return WindowInsetsCompat
        .toWindowInsetsCompat(rootWindowInsets)
        .isVisible(WindowInsetsCompat.Type.ime())
}
