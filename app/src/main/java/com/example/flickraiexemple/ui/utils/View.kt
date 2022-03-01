package com.example.flickraiexemple.ui.utils

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("app:hide")
fun View.hide(hide: Boolean) {
    visibility = if (hide) View.GONE else View.VISIBLE
}

@BindingAdapter("app:show")
fun View.show(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}

