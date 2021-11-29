package com.example.weatherapplication.useCases

import com.example.weatherapplication.di.AppComponent
import com.example.weatherapplication.di.DaggerAppComponent
import com.example.weatherapplication.screens.second.entities.WeatherTo5Days
import com.example.weatherapplication.screens.second.networking.mapper.Weather5DayMapper.toWeather5day


class LoadWeather5DayUseCase {
    private val appComponent: AppComponent = DaggerAppComponent.create()
    private val weatherRepository = appComponent.repository

    suspend fun loadWeatherTo5Days(lat: String, lon: String): List<WeatherTo5Days>? {
        val response = weatherRepository.loadWeatherTo5Days(lat, lon)
        return if (response.isSuccessful) {
            response.body()?.toWeather5day()
        } else {
            throw Throwable(response.errorBody().toString())
        }
    }
}