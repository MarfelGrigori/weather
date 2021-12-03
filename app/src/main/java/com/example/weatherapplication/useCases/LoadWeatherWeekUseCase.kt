package com.example.weatherapplication.useCases

import com.example.weatherapplication.repository.WeatherRepository
import com.example.weatherapplication.screens.home.entities.WeatherWeek
import com.example.weatherapplication.screens.home.networking.toWeatherWeek
import javax.inject.Inject

class LoadWeatherWeekUseCase @Inject constructor(private val weatherRepository : WeatherRepository) {


    suspend fun loadWeatherWeek(lat: String, lon: String): List<WeatherWeek>? {
        val response = weatherRepository.loadWeatherWeek(lat, lon)
        return if (response.isSuccessful) {
            response.body()?.toWeatherWeek()
        } else {
            throw Throwable(response.errorBody().toString())
        }
    }
}