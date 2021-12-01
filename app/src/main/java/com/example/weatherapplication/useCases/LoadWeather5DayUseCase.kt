package com.example.weatherapplication.useCases

import com.example.weatherapplication.di.AppComponent
import com.example.weatherapplication.repository.WeatherRepository
import com.example.weatherapplication.screens.second.entities.WeatherTo5Days
import com.example.weatherapplication.screens.second.networking.mapper.toWeather5day
import javax.inject.Inject


class LoadWeather5DayUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
//    private val appComponent: AppComponent = DaggerAppComponent.create()
//    private val weatherRepository = appComponent.repository

    suspend fun loadWeatherTo5Days(lat: String, lon: String): List<WeatherTo5Days>? {
        val response = weatherRepository.loadWeatherTo5Days(lat, lon)
        return if (response.isSuccessful) {
            response.body()?.toWeather5day()
        } else {
            throw Throwable(response.errorBody().toString())
        }
    }
}