package com.example.weatherapplication.repository

import com.example.weatherapplication.networking.weather.WeatherApi
import com.example.weatherapplication.response.Weather5DaysResponse
import com.example.weatherapplication.response.WeatherTodayResponse
import retrofit2.Response

class WeatherRepository {
    private val api= WeatherApi.provideRetrofit()

    suspend fun loadWeatherToday(lat:String, lon:String, appid:String): Response<WeatherTodayResponse> {
        return  api.loadWeatherToday(lat, lon, "metric",appid)
    }

    suspend fun loadWeatherTo5Days(lat:String, lon:String, appid:String): Response<Weather5DaysResponse> {
        return api.loadWeatherTo5Days(lat, lon, 5,"metric",appid)
    }
}