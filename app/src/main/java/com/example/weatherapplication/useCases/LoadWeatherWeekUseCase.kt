package com.example.weatherapplication.useCases

import com.example.weatherapplication.screens.first.entities.WeatherWeek
import com.example.weatherapplication.screens.first.networking.WeatherWeekMapper.toWeatherWeek
import com.example.weatherapplication.repository.WeatherRepository

class LoadWeatherWeekUseCase {
    private val weatherRepository = WeatherRepository()
    suspend fun loadWeatherWeek(lat: String, lon: String): List<WeatherWeek>? {
        val response = weatherRepository.loadWeatherWeek(lat, lon)
        return if (response.isSuccessful) {
            response.body()?.toWeatherWeek()
        } else {
            throw Throwable(response.errorBody().toString())
        }
    }
}