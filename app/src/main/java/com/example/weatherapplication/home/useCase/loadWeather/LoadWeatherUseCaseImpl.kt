package com.example.weatherapplication.home.useCase.loadWeather


import com.example.weatherapplication.common.repository.WeatherServerImpl
import com.example.weatherapplication.home.useCase.loadWeather.networking.WeatherTodayResponse
import com.example.weatherapplication.home.useCase.loadWeather.networking.WeatherWeekResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

open class LoadWeatherUseCaseImpl @Inject constructor(private val weatherServer: WeatherServerImpl) :
    LoadWeatherUseCase {
        override operator fun invoke(lat: String, lon: String): Single<Pair<WeatherTodayResponse, WeatherWeekResponse>> =
        weatherServer.loadWeatherToday(lat, lon)
            .zipWith(weatherServer.loadWeatherWeek(lat, lon), ::Pair)
}