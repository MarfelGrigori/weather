package com.example.weatherapplication.home.useCase.loadWeather

import com.example.weatherapplication.home.useCase.loadWeather.networking.WeatherTodayResponse
import com.example.weatherapplication.home.useCase.loadWeather.networking.WeatherWeekResponse
import io.reactivex.rxjava3.core.Single


interface LoadWeatherForHomeScreen {
   operator fun invoke(lat: String, lon: String): Single<Pair<WeatherTodayResponse, WeatherWeekResponse>>
}
