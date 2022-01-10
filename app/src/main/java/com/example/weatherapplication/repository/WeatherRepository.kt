package com.example.weatherapplication.repository

import com.example.weatherapplication.networking.weather.WeatherApi
import com.example.weatherapplication.screens.home.networking.WeatherTodayResponse
import com.example.weatherapplication.screens.home.networking.WeatherWeekResponse
import com.example.weatherapplication.screens.weatherday.networking.response.WeatherDayResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

open class WeatherRepository @Inject constructor(private val api: WeatherApi) {

    private val KEY = "a5000964c71443402a055b2152004987"
    fun loadWeatherToday(lat: String, lon: String): Observable<WeatherTodayResponse> {
        return api.provideRetrofit().loadWeatherToday(lat, lon, "metric", "ru", KEY)
    }

     fun loadWeatherTo5Days(lat: String, lon: String): Observable<WeatherDayResponse> {
        return api.provideRetrofit().loadWeatherDay(lat, lon, "metric", "ru", KEY)
    }

     fun loadWeatherWeek(lat: String, lon: String): Observable<WeatherWeekResponse> {
        return api.provideRetrofit().loadWeatherWeek(lat, lon, "currently", "metric", "ru", KEY)
    }
}