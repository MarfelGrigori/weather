package com.example.weatherapplication.screens.weatherday.entities

data class WeatherDay(
    var newDay: String? = null,
    val time: Long,
    val text: String,
    val temp: Double?,
    val pressure: Int?,
    val wind: Int
)