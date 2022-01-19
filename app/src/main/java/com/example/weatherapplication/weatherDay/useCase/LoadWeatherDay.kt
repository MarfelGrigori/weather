package com.example.weatherapplication.weatherDay.useCase

import com.example.weatherapplication.weatherDay.useCase.networking.response.WeatherDayResponse
import io.reactivex.rxjava3.core.Single

interface LoadWeatherDay {
    fun loadWeatherDay(lat: String, lon: String): Single<WeatherDayResponse>
}