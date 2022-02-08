package com.example.weatherapplication.weatherDay.useCases.loadWeather

import com.example.weatherapplication.weatherDay.useCases.loadWeather.networking.response.WeatherDayResponse
import io.reactivex.rxjava3.core.Single

interface LoadWeatherUseCase {
    operator fun invoke(lat: String, lon: String): Single<WeatherDayResponse>
}

