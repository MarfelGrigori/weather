package com.example.weatherapplication.useCases

import com.example.weatherapplication.entities.WeatherWeek
import com.example.weatherapplication.networking.mapper.WeatherWeekMapper.toWeatherWeek
import com.example.weatherapplication.repository.WeatherRepository

class LoadWeatherWeekUseCase {
    private val weatherRepository = WeatherRepository()
    suspend fun loadWeatherWeek(lat: String, lon: String, appid: String): List<WeatherWeek>? {
        val response = weatherRepository.loadWeatherWeek(lat, lon, appid)
        return if (response.isSuccessful) {
            response.body()?.toWeatherWeek()
        } else {
            throw Throwable(response.errorBody().toString())
        }
    }
}