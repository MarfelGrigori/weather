package com.example.weatherapplication.networking

import com.example.weatherapplication.screens.home.networking.WeatherTodayResponse
import com.example.weatherapplication.screens.home.networking.WeatherWeekResponse
import com.example.weatherapplication.screens.weatherday.networking.response.WeatherDayResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("data/2.5/weather")
    suspend fun loadWeatherToday(
        @Query("lat")
        lat: String,
        @Query("lon")
        lon: String,
        @Query("units")
        units: String,
        @Query("appid")
        appid: String
    ): Response<WeatherTodayResponse>

    @GET("data/2.5/forecast")
    suspend fun loadWeatherDay(
        @Query("lat")
        lat: String,
        @Query("lon")
        lon: String,
//        @Query("cnt")
//        cnt: Int = 8,
        @Query("units")
        units: String,
        @Query("appid")
        appid: String
    ): Response<WeatherDayResponse>

    @GET("data/2.5/onecall")
    suspend fun loadWeatherWeek(
        @Query("lat")
        lat: String,
        @Query("lon")
        lon: String,
        @Query("exclude")
        cnt: String = "currently",
        @Query("units")
        units: String,
        @Query("appid")
        appid: String
    ): Response<WeatherWeekResponse>
}