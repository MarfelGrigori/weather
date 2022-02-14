package com.example.weatherapplication.common.di

import com.example.weatherapplication.common.repository.WeatherServer
import com.example.weatherapplication.common.repository.WeatherServerImpl
import com.example.weatherapplication.common.utils.LocationService
import com.example.weatherapplication.common.utils.LocationServiceInterface
import com.example.weatherapplication.home.useCase.loadWeather.LoadWeatherUseCase
import com.example.weatherapplication.home.useCase.loadWeather.LoadWeatherUseCaseImpl
import dagger.Binds
import dagger.Module


@Module
abstract class DependenciesModule {
    @Binds
    abstract fun weatherService(impl: WeatherServerImpl?): WeatherServer?

    @Binds
    abstract fun loadWeatherUseCase(impl: LoadWeatherUseCaseImpl?): LoadWeatherUseCase?

    @Binds
    abstract fun loadWeatherDayUseCase(impl: com.example.weatherapplication.weatherDay.useCases.loadWeather.LoadWeatherUseCaseImpl?): com.example.weatherapplication.weatherDay.useCases.loadWeather.LoadWeatherUseCase?

    @Binds
    abstract fun locationService(impl : LocationService?) : LocationServiceInterface
}
