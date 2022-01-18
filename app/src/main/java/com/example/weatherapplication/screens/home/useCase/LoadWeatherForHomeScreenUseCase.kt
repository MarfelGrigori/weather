package com.example.weatherapplication.screens.home.useCase


import com.example.weatherapplication.screens.common.repository.WeatherRepository
import com.example.weatherapplication.screens.home.useCase.networking.WeatherTodayResponse
import com.example.weatherapplication.screens.home.useCase.networking.WeatherWeekResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

open class LoadWeatherForHomeScreenUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {

    fun loadWeather(
        lat: String,
        lon: String
    ): Single<Pair<WeatherTodayResponse, WeatherWeekResponse>> {
        return weatherRepository.loadWeatherToday(lat, lon).zipWith(
            weatherRepository.loadWeatherWeek(
                lat,
                lon
            ), { first, second -> Pair(first, second) }
        )
    }
}