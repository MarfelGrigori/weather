package com.example.weatherapplication.screens.home.entities

data class WeatherWeekWithAllParameters(
    val date: String,
    val day: String,
    val text: String,
    val temp: String,
    val pressure: String?,
    val wind: String
)
