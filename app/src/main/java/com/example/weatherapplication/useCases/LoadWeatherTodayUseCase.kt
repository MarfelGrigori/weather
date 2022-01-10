package com.example.weatherapplication.useCases


import com.example.weatherapplication.repository.WeatherRepository
import com.example.weatherapplication.screens.home.networking.WeatherTodayResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

open class LoadWeatherTodayUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {

    fun loadWeatherToday(lat: String, lon: String): Observable<WeatherTodayResponse> {
        return weatherRepository.loadWeatherToday(lat, lon)
    }
}