package com.example.weatherapplication.home.useCase.models

import com.example.weatherapplication.common.utils.Picture

data class WeatherWeekWithAllParameters(
    val date: String,
    val day: String,
    val text: String,
    val temp: String,
    val pressure: String?,
    val wind: String,
    val picture: Picture
)
