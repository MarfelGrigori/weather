package com.example.weatherapplication.home.useCase.loadWeather

import com.example.weatherapplication.home.useCase.loadWeather.networking.WeatherTodayResponse
import com.example.weatherapplication.home.useCase.loadWeather.networking.WeatherWeekResponse
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.core.Single

interface LoadWeatherUseCase {
   operator fun invoke(lat: String, lon: String): Single<Pair<WeatherTodayResponse, WeatherWeekResponse>>
}
