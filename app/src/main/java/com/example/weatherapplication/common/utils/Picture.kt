package com.example.weatherapplication.common.utils

import android.widget.ImageView
import com.example.weatherapplication.R

enum class Picture(val main: String, val imageResource: Int) {
    CLOUDS("пасмурно", R.drawable.cloud),
    CLOUDS_("облачно с прояснениями", R.drawable.cloud),
    CLOUDS_C("переменная облачность", R.drawable.cloud),
    CLOUDS_S("небольшая облачность", R.drawable.cloud),
    RAIN("дождь", R.drawable.union),
    SMALL_RAIN("небольшой дождь", R.drawable.union),
    HEAVY_RAIN("сильный дождь",R.drawable.union),
    RAIN_SNOW("снег с дождём", R.drawable.union),
    CLEAR("ясно", R.drawable.sun),
    SNOW("снег", R.drawable.snow),
    SNOW_S("небольшой снег", R.drawable.snow),
    UNKNOWN("", R.drawable.ic_android_black_24dp)
}

fun String.toPicture(): Picture {
    return when (this) {
        (Picture.CLOUDS.main) -> {
            Picture.CLOUDS
        }
        (Picture.CLOUDS_.main) -> {
            Picture.CLOUDS
        }
        (Picture.CLOUDS_S.main) -> {
            Picture.CLOUDS
        }
        (Picture.RAIN_SNOW.main) -> {
            Picture.RAIN
        }
        (Picture.CLOUDS_C.main) -> {
            Picture.CLOUDS
        }
        (Picture.RAIN.main) -> {
            Picture.RAIN
        }
        (Picture.SMALL_RAIN.main) -> {
            Picture.RAIN
        }
        (Picture.HEAVY_RAIN.main) -> {
            Picture.RAIN
        }
        (Picture.CLEAR.main) -> {
            Picture.CLEAR
        }
        (Picture.SNOW.main) -> {
            Picture.SNOW
        }
        (Picture.SNOW_S.main) -> {
            Picture.SNOW
        }
        else -> Picture.UNKNOWN
    }
}

fun Picture.setPicture(image: ImageView?) = image?.setImageResource(this.imageResource)


