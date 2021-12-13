package com.example.weatherapplication.screens.home.networking

import com.example.weatherapplication.screens.home.entities.WeatherToday
import com.example.weatherapplication.utils.Converter.degToWindRoze


fun WeatherTodayResponse.toWeatherToday(): WeatherToday = WeatherToday(
    city = this.name ?: "",
    country = this.sys?.country ?: "",
    temp = this.main?.temp?.toInt() ?: -50000,
    main = this.weather?.get(0)?.description ?: "",
    humidity = this.main?.humidity ?: -1,
    rain = this.rain?.oneH ?: 0.0,
    snow = this.snow?.oneH ?: -1.0,
    pressure = this.main?.pressure ?: -1,
    speed = this.wind?.speed ?: -1.0,
    deg = this.wind?.deg?.degToWindRoze().toString()
)
