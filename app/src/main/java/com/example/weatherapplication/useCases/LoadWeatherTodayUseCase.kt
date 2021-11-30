package com.example.weatherapplication.useCases


import com.example.weatherapplication.repository.WeatherRepository
import com.example.weatherapplication.screens.first.entities.WeatherToday
import com.example.weatherapplication.screens.first.networking.toWeatherToday

class LoadWeatherTodayUseCase {
    private val weatherRepository = WeatherRepository()

    suspend fun loadWeatherToday(lat: String, lon: String): WeatherToday? {
        val response = weatherRepository.loadWeatherToday(lat, lon)
        return if (response.isSuccessful) {
            response.body()?.toWeatherToday()
        } else {
            throw Throwable(response.errorBody().toString())
        }
    }
}