package com.example.weatherapplication.screens.home.entities

 data class WeatherToday(
    val city: String,
    val country: String,
    val temp: Int,
    val main: String,
    val humidity: Int,
    val rain: Double,
    val snow: Double,
    val pressure: Int,
    val speed: Double,
    val deg: String
)

