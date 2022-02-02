package com.example.weatherapplication.common.repository

import com.example.weatherapplication.common.networking.weather.WeatherApi
import com.example.weatherapplication.home.useCase.loadWeather.networking.WeatherTodayResponse
import com.example.weatherapplication.home.useCase.loadWeather.networking.WeatherWeekResponse
import com.example.weatherapplication.weatherDay.useCases.loadWeather.networking.response.WeatherDayResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

private const val KEY = "a5000964c71443402a055b2152004987"

open class WeatherServerImpl @Inject constructor(private val api: WeatherApi) : WeatherServer {

    override fun loadWeatherToday(lat: String, lon: String): Single<WeatherTodayResponse> {
        return api.provideRetrofit().loadWeatherToday(lat, lon, "metric", "ru", KEY)
    }

    override fun loadWeatherTo5Days(lat: String, lon: String): Single<WeatherDayResponse> {
        return api.provideRetrofit().loadWeatherDay(lat, lon, "metric", "ru", KEY)
    }

    override fun loadWeatherWeek(lat: String, lon: String): Single<WeatherWeekResponse> {
        return api.provideRetrofit().loadWeatherWeek(lat, lon, "currently", "metric", "ru", KEY)
    }

}