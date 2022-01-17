package com.example.core.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.core.R

fun ImageView.loadImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .error(R.drawable.ic_error_24)
        .centerCrop()
        .into(this)
}