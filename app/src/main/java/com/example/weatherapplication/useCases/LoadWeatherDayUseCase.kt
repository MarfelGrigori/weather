package com.example.weatherapplication.useCases

import com.example.weatherapplication.repository.WeatherRepository
import com.example.weatherapplication.screens.weatherday.entities.WeatherDay
import com.example.weatherapplication.screens.weatherday.networking.mapper.toWeatherDay
import javax.inject.Inject


class LoadWeatherDayUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {

    suspend fun loadWeatherDay(lat: String, lon: String): List<WeatherDay>? {
        val response = weatherRepository.loadWeatherTo5Days(lat, lon)
        return if (response.isSuccessful) {
            response.body()?.toWeatherDay()
        } else {
            throw Throwable(response.errorBody().toString())
        }
    }
}