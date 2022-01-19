package com.example.weatherapplication.weatherDay.useCase.loadWeather

import com.example.weatherapplication.common.repository.WeatherServer
import com.example.weatherapplication.weatherDay.useCase.loadWeather.networking.response.WeatherDayResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


open class LoadWeatherForDayScreenUseCase @Inject constructor(private val weatherServer: WeatherServer):
    LoadWeatherDay {
    operator fun invoke(lat: String, lon: String): Single<WeatherDayResponse> =
        weatherServer.loadWeatherTo5Days(lat, lon)
}