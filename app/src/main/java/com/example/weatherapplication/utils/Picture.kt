package com.example.weatherapplication.utils

import android.util.Log
import android.widget.ImageView
import com.example.weatherapplication.R

enum class Picture(val main : String) {
    CLOUDS("Clouds"),
    RAIN("Rain"),
    CLEAR("Clear"),
    SNOW("Snow")}
    fun setPicture (string:String,image:ImageView){
        when (string) {
            (Picture.CLOUDS.main) -> {
                image.setImageResource(R.drawable.cloud)
            }
            (Picture.RAIN.main) -> {
                image.setImageResource(R.drawable.union)
            }
            (Picture.CLEAR.main) -> {
                image.setImageResource(R.drawable.sun)
            }
            (Picture.SNOW.main) -> {
                image.setImageResource(R.drawable.snow)
            }
        }
    }
