package com.example.weatherapplication.useCases

import com.example.weatherapplication.repository.WeatherRepository
import com.example.weatherapplication.screens.home.networking.WeatherWeekResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

open class LoadWeatherWeekUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {

    fun loadWeatherWeek(lat: String, lon: String): Single<WeatherWeekResponse> {
        return weatherRepository.loadWeatherWeek(lat, lon)
    }
}