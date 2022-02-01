package com.example.weatherapplication.weatherDay.useCases.loadWeather

import com.example.weatherapplication.common.repository.WeatherServer
import com.example.weatherapplication.weatherDay.useCases.loadWeather.networking.response.WeatherDayResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


open class LoadWeatherUseCaseImpl @Inject constructor(private val weatherServer: WeatherServer): LoadWeatherUseCase {
    override operator fun invoke(lat: String, lon: String): Single<WeatherDayResponse> =
        weatherServer.loadWeatherTo5Days(lat, lon)
}