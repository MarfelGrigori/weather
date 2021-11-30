package com.example.weatherapplication.utils

import android.widget.ImageView
import com.example.weatherapplication.R
import java.lang.IllegalStateException

enum class Picture(val main: String,val imageResource : Int) {
    CLOUDS("Clouds",R.drawable.cloud),
    RAIN("Rain",R.drawable.union),
    CLEAR("Clear",R.drawable.sun),
    SNOW("Snow",R.drawable.snow)
}

    fun String.toPicture (): Picture {
        return when (this) {
            (Picture.CLOUDS.main) -> {
                Picture.CLOUDS
            }
            (Picture.RAIN.main) -> {
                Picture.RAIN
            }
            (Picture.CLEAR.main) -> {
                Picture.CLEAR
            }
            (Picture.SNOW.main) -> {
                Picture.SNOW
            }
            else -> throw IllegalStateException("${R.string.out_of_enum}")
        }
    }
fun Picture.setPicture(image: ImageView)= image.setImageResource(this.imageResource)


