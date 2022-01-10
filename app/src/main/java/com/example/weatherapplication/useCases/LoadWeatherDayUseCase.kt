package com.example.weatherapplication.useCases

import com.example.weatherapplication.repository.WeatherRepository
import com.example.weatherapplication.screens.weatherday.networking.response.WeatherDayResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject


open class LoadWeatherDayUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {

    fun loadWeatherDay(lat: String, lon: String): Observable<WeatherDayResponse> {
        return weatherRepository.loadWeatherTo5Days(lat, lon)
    }
}