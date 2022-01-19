package com.example.weatherapplication.home.useCase


import com.example.weatherapplication.common.repository.WeatherRepository
import com.example.weatherapplication.home.useCase.networking.WeatherTodayResponse
import com.example.weatherapplication.home.useCase.networking.WeatherWeekResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

open class LoadWeatherForHomeScreenUseCase @Inject constructor(private val weatherRepository: WeatherRepository) : LoadWeatherForHomeScreen {
    override fun loadWeather(lat: String, lon: String): Single<Pair<WeatherTodayResponse, WeatherWeekResponse>> =
        weatherRepository.loadWeatherToday(lat, lon)
            .zipWith(weatherRepository.loadWeatherWeek(lat, lon), ::Pair)
}