package com.example.weatherapplication.repository

import com.example.weatherapplication.di.AppComponent
import com.example.weatherapplication.di.DaggerAppComponent
import com.example.weatherapplication.networking.weather.WeatherApi
import com.example.weatherapplication.screens.first.networking.WeatherTodayResponse
import com.example.weatherapplication.screens.first.networking.WeatherWeekResponse
import com.example.weatherapplication.screens.second.networking.response.Weather5DaysResponse
import retrofit2.Response
import javax.inject.Inject

class WeatherRepository @Inject constructor(private  val api : WeatherApi) {


    private val KEY = "a5000964c71443402a055b2152004987"
    suspend fun loadWeatherToday(lat: String, lon: String): Response<WeatherTodayResponse> {
        return api.provideRetrofit().loadWeatherToday(lat, lon, "metric", KEY)
    }

    suspend fun loadWeatherTo5Days(lat: String, lon: String): Response<Weather5DaysResponse> {
        return api.provideRetrofit().loadWeatherTo5Days(lat, lon, 8, "metric", KEY)
    }

    suspend fun loadWeatherWeek(lat: String, lon: String): Response<WeatherWeekResponse> {
        return api.provideRetrofit().loadWeatherWeek(lat, lon, "currently", "metric", KEY)
    }

}