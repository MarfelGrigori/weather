package com.example.weatherapplication.domain.screens.home.useCase.entities

import com.example.weatherapplication.utils.Picture

data class WeatherWeekWithAllParameters(
    val date: String,
    val day: String,
    val text: String,
    val temp: String,
    val pressure: String?,
    val wind: String,
    val picture: Picture
)
