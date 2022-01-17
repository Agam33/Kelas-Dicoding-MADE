package com.example.core.utils

import android.content.res.ColorStateList
import android.graphics.Color
import com.example.core.BuildConfig

const val API_KEY = BuildConfig.KEY
const val BASE_URL = "https://api.themoviedb.org/3/"
const val GET_IMAGE = "https://image.tmdb.org/t/p/original"

fun getColorFloatingButton(state: Boolean) : ColorStateList {
    return if(state) ColorStateList.valueOf(Color.rgb(245,60,60))
    else ColorStateList.valueOf(Color.rgb(0,0,0))
}
