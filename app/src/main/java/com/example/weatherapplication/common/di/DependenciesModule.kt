package com.example.weatherapplication.common.di

import android.content.Context
import com.example.weatherapplication.common.networking.WeatherService
import com.example.weatherapplication.common.repository.WeatherServer
import com.example.weatherapplication.common.repository.WeatherServerImpl
import com.example.weatherapplication.common.utils.LocationServiceImpl
import com.example.weatherapplication.common.utils.LocationService
import com.example.weatherapplication.home.useCase.loadWeather.LoadWeatherUseCase
import com.example.weatherapplication.home.useCase.loadWeather.LoadWeatherUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
 class DependenciesModule {
    @Provides
     fun weatherService(api:WeatherService): WeatherServer = WeatherServerImpl(api)

    @Provides
     fun loadWeatherUseCase(repository: WeatherServer):LoadWeatherUseCase = LoadWeatherUseCaseImpl(repository)

    @Provides
     fun loadWeatherDayUseCase(repository: WeatherServer):com.example.weatherapplication.weatherDay.useCases.loadWeather.LoadWeatherUseCase = com.example.weatherapplication.weatherDay.useCases.loadWeather.LoadWeatherUseCaseImpl(repository)

    @Provides
     fun locationService(context:Context) : LocationService = LocationServiceImpl(context)
}
