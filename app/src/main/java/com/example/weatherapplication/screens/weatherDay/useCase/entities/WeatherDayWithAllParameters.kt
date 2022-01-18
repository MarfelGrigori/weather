package com.example.weatherapplication.screens.weatherDay.useCase.entities

import com.example.weatherapplication.screens.common.utils.Picture

class WeatherDayWithAllParameters(
    var newDay: String? = null,
    val time: String,
    val text: String,
    val temp: Double?,
    val pressure: Int?,
    val wind: Int,
    val picture: Picture
)



