package com.example.weatherapplication.networking

import com.example.weatherapplication.response.Weather5DaysResponse
import com.example.weatherapplication.response.WeatherTodayResponse
import com.example.weatherapplication.response.WeatherWeekResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("data/2.5/weather")
    suspend fun loadWeatherToday(
        @Query("lat")
        lat:String,
        @Query("lon")
        lon:String,
        @Query("units")
        units:String,
        @Query("appid")
        appid:String): Response<WeatherTodayResponse>

    @GET("data/2.5/forecast")
    suspend fun loadWeatherTo5Days(
        @Query("lat")
        lat:String,
        @Query("lon")
        lon:String,
        @Query("cnt")
        cnt:Int =8,
        @Query("units")
        units:String,
        @Query("appid")
        appid:String): Response<Weather5DaysResponse>

    @GET("data/2.5/onecall")
    suspend fun loadWeatherWeek(
        @Query("lat")
        lat:String,
        @Query("lon")
        lon:String,
        @Query("exclude")
        cnt:String ="currently",
        @Query("units")
        units:String,
        @Query("appid")
        appid:String): Response<WeatherWeekResponse>
}