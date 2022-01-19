package com.example.weatherapplication.home.useCase.models

import com.example.weatherapplication.common.utils.Picture

data class WeatherToday(
    val city: String,
    val country: String,
    val temp: String,
    val main: String,
    val humidity: Int,
    val rain: Double,
    val snow: Double,
    val pressure: Int,
    val speed: Double,
    val deg: String,
    val picture: Picture?
)

