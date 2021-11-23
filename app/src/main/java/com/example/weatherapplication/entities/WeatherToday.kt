package com.example.weatherapplication.entities

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
) {

    fun weather(): String {
        return "$temp °C | $main"
    }
}