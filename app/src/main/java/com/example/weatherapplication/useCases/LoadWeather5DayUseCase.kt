package com.example.weatherapplication.useCases

import com.example.weatherapplication.entities.WeatherTo5Days
import com.example.weatherapplication.repository.WeatherRepository
import com.example.weatherapplication.response.Weather5DaysResponse.Companion.toWeather5day


class LoadWeather5DayUseCase {

    private val weatherRepository = WeatherRepository()

    suspend fun loadWeatherTo5Days(lat: String, lon: String, appid: String): List<WeatherTo5Days>? {
        val response = weatherRepository.loadWeatherTo5Days(lat, lon, appid)
        return if (response.isSuccessful) {
            response.body()?.let {
               it.toWeather5day(it)
            }
        } else {
            throw Throwable(response.errorBody().toString())
        }
    }
}