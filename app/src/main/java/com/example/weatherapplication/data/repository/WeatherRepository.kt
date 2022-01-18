package com.example.weatherapplication.data.repository

import com.example.weatherapplication.data.networking.weather.WeatherApi
import com.example.weatherapplication.domain.screens.home.useCase.networking.WeatherTodayResponse
import com.example.weatherapplication.domain.screens.home.useCase.networking.WeatherWeekResponse
import com.example.weatherapplication.domain.screens.weatherday.useCase.networking.response.WeatherDayResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

open class WeatherRepository @Inject constructor(private val api: WeatherApi) {

    private val KEY = "a5000964c71443402a055b2152004987"
    fun loadWeatherToday(lat: String, lon: String): Single<WeatherTodayResponse> {
        return api.provideRetrofit().loadWeatherToday(lat, lon, "metric", "ru", KEY)
    }

     fun loadWeatherTo5Days(lat: String, lon: String): Single<WeatherDayResponse> {
        return api.provideRetrofit().loadWeatherDay(lat, lon, "metric", "ru", KEY)
    }

     fun loadWeatherWeek(lat: String, lon: String): Single<WeatherWeekResponse> {
        return api.provideRetrofit().loadWeatherWeek(lat, lon, "currently", "metric", "ru", KEY)
    }

}