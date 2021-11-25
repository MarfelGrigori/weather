package com.example.weatherapplication.screens.first.entities

data class WeatherWeek(
    val time: Long,
    val text: String,
    val temp: Int,
    val pressure: Int?,
    val wind: Int
)
