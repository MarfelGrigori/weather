package com.example.weatherapplication.home.mappers

import com.example.weatherapplication.home.models.WeatherToday
import com.example.weatherapplication.common.utils.Converter.degToWindRoze
import com.example.weatherapplication.common.utils.toPicture
import com.example.weatherapplication.home.useCase.loadWeather.networking.WeatherTodayResponse


fun WeatherTodayResponse.toWeatherToday(): WeatherToday = WeatherToday(
    city = this.name ?: "",
    country = this.sys?.country ?: "",
    temp = this.main?.temp?.toInt().toString(),
    main = this.weather?.get(0)?.description ?: "",
    humidity = this.main?.humidity ?: -1,
    rain = this.rain?.oneH ?: 0.0,
    snow = this.snow?.oneH ?: -1.0,
    pressure = this.main?.pressure ?: -1,
    speed = this.wind?.speed ?: -1.0,
    deg = this.wind?.deg?.degToWindRoze().toString(),
    picture = this.weather?.get(0)?.description?.toPicture()
)
