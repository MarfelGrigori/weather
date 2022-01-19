package com.example.weatherapplication.home.useCase


import com.example.weatherapplication.common.repository.WeatherServer
import com.example.weatherapplication.home.useCase.networking.WeatherTodayResponse
import com.example.weatherapplication.home.useCase.networking.WeatherWeekResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

open class LoadWeatherForHomeScreenUseCase @Inject constructor(private val weatherServer: WeatherServer) : LoadWeatherForHomeScreen {
    override fun loadWeather(lat: String, lon: String): Single<Pair<WeatherTodayResponse, WeatherWeekResponse>> =
        weatherServer.loadWeatherToday(lat, lon)
            .zipWith(weatherServer.loadWeatherWeek(lat, lon), ::Pair)
}