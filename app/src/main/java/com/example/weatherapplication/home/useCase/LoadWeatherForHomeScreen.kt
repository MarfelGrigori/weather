package com.example.weatherapplication.home.useCase

import com.example.weatherapplication.home.useCase.networking.WeatherTodayResponse
import com.example.weatherapplication.home.useCase.networking.WeatherWeekResponse
import io.reactivex.rxjava3.core.Single

interface LoadWeatherForHomeScreen {
    fun loadWeather (lat: String, lon: String): Single<Pair<WeatherTodayResponse, WeatherWeekResponse>>
}