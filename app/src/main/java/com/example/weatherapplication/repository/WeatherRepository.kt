package com.example.weatherapplication.repository

import com.example.weatherapplication.networking.weather.WeatherApi
import com.example.weatherapplication.networking.response.Weather5DaysResponse
import com.example.weatherapplication.networking.response.WeatherTodayResponse
import com.example.weatherapplication.networking.response.WeatherWeekResponse
import retrofit2.Response

class WeatherRepository {
    private val api= WeatherApi.provideRetrofit()

    suspend fun loadWeatherToday(lat:String, lon:String, appid:String): Response<WeatherTodayResponse> {
        return  api.loadWeatherToday(lat, lon, "metric",appid)
    }

    suspend fun loadWeatherTo5Days(lat:String, lon:String, appid:String): Response<Weather5DaysResponse> {
        return api.loadWeatherTo5Days(lat, lon, 8,"metric",appid)
    }
    suspend fun loadWeatherWeek(lat:String, lon:String, appid:String): Response<WeatherWeekResponse> {
        return api.loadWeatherWeek(lat,lon,"currently","metric",appid )
    }

}