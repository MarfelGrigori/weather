package com.example.weatherapplication.common.repository

import com.example.weatherapplication.home.useCase.loadWeather.networking.WeatherTodayResponse
import com.example.weatherapplication.home.useCase.loadWeather.networking.WeatherWeekResponse
import com.example.weatherapplication.weatherDay.useCases.loadWeather.networking.response.WeatherDayResponse
import io.reactivex.rxjava3.core.Single

interface WeatherServer {
    fun loadWeatherToday(lat: String, lon: String): Single<WeatherTodayResponse>
    fun loadWeatherTo5Days(lat: String, lon: String): Single<WeatherDayResponse>
    fun loadWeatherWeek(lat: String, lon: String): Single<WeatherWeekResponse>
}