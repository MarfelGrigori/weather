package com.example.weatherapplication.screens.home.useCase.entities

import com.example.weatherapplication.screens.common.utils.Picture

data class WeatherWeekWithAllParameters(
    val date: String,
    val day: String,
    val text: String,
    val temp: String,
    val pressure: String?,
    val wind: String,
    val picture: Picture
)
