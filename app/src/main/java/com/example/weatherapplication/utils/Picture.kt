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
                Log.e("TAG",Picture.CLOUDS.main)
            }
            (Picture.RAIN.main) -> {
                image.setImageResource(R.drawable.union)
                Log.e("TAG",Picture.RAIN.main)
            }
            (Picture.CLEAR.main) -> {
                image.setImageResource(R.drawable.sun)
            }
            (Picture.SNOW.main) -> {
                image.setImageResource(R.drawable.snow)
                Log.e("TAG",Picture.SNOW.main)
            }
        }
    }
