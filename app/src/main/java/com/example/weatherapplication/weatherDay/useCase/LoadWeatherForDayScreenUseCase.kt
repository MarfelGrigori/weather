package com.example.weatherapplication.weatherDay.useCase

import com.example.weatherapplication.common.repository.WeatherRepository
import com.example.weatherapplication.weatherDay.useCase.networking.response.WeatherDayResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


open class LoadWeatherForDayScreenUseCase @Inject constructor(private val weatherRepository: WeatherRepository):LoadWeatherDay {
    override fun loadWeatherDay(lat: String, lon: String): Single<WeatherDayResponse> =
        weatherRepository.loadWeatherTo5Days(lat, lon)
}