package com.example.weatherapplication.domain.screens.weatherday.useCase.entities

import com.example.weatherapplication.utils.Picture

class WeatherDayWithAllParameters(
    var newDay: String? = null,
    val time: String,
    val text: String,
    val temp: Double?,
    val pressure: Int?,
    val wind: Int,
    val picture: Picture
)



