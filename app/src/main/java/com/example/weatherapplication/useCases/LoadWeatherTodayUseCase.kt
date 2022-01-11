package com.example.weatherapplication.useCases


import com.example.weatherapplication.repository.WeatherRepository
import com.example.weatherapplication.screens.home.networking.WeatherTodayResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

open class LoadWeatherTodayUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {

    fun loadWeatherToday(lat: String, lon: String): Single<WeatherTodayResponse> {
        return weatherRepository.loadWeatherToday(lat, lon)
    }
}