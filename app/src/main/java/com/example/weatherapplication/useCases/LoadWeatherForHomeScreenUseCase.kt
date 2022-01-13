package com.example.weatherapplication.useCases


import com.example.weatherapplication.repository.WeatherRepository
import com.example.weatherapplication.screens.home.networking.WeatherTodayResponse
import com.example.weatherapplication.screens.home.networking.WeatherWeekResponse
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