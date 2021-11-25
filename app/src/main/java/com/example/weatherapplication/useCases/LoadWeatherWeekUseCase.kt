package com.example.weatherapplication.useCases

import com.example.weatherapplication.entities.WeatherWeek
import com.example.weatherapplication.repository.WeatherRepository
import com.example.weatherapplication.response.WeatherWeekResponse.Companion.toWeatherWeek

class LoadWeatherWeekUseCase {
    private val weatherRepository = WeatherRepository()
    suspend fun loadWeatherWeek(lat: String, lon: String, appid: String): List<WeatherWeek>? {
        val response = weatherRepository.loadWeatherWeek(lat, lon, appid)
        return if (response.isSuccessful) {
            response.body()?.let {
                it.toWeatherWeek(it)
            }
        } else {
            throw Throwable(response.errorBody().toString())
        }
    }
}