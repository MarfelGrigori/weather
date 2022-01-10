package com.example.weatherapplication.networking

import com.example.weatherapplication.screens.home.networking.WeatherTodayResponse
import com.example.weatherapplication.screens.home.networking.WeatherWeekResponse
import com.example.weatherapplication.screens.weatherday.networking.response.WeatherDayResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("data/2.5/weather")
     fun loadWeatherToday(
        @Query("lat")
        lat: String,
        @Query("lon")
        lon: String,
        @Query("units")
        units: String,
        @Query("lang")
        language: String,
        @Query("appid")
        appid: String
    ): Observable<WeatherTodayResponse>

    @GET("data/2.5/forecast")
     fun loadWeatherDay(
        @Query("lat")
        lat: String,
        @Query("lon")
        lon: String,
        @Query("units")
        units: String,
        @Query("lang")
        language: String,
        @Query("appid")
        appid: String
    ): Observable<WeatherDayResponse>

    @GET("data/2.5/onecall")
     fun loadWeatherWeek(
        @Query("lat")
        lat: String,
        @Query("lon")
        lon: String,
        @Query("exclude")
        cnt: String = "currently",
        @Query("units")
        units: String,
        @Query("lang")
        language: String,
        @Query("appid")
        appid: String
    ): Observable<WeatherWeekResponse>
}