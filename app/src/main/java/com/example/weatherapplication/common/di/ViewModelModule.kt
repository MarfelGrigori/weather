package com.example.weatherapplication.common.di

import androidx.lifecycle.ViewModel
import com.example.weatherapplication.home.useCase.loadWeather.LoadWeatherUseCase
import com.example.weatherapplication.home.viewModel.HomeViewModel
import com.example.weatherapplication.weatherDay.viewModel.WeatherDayViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


@Module
 class ViewModelModule {

    @Provides
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
     fun bindMainViewModel(loadWeatherTodayUseCase: LoadWeatherUseCase): ViewModel = HomeViewModel(loadWeatherTodayUseCase)

    @Provides
    @IntoMap
    @ViewModelKey(WeatherDayViewModel::class)
     fun bindSecondViewModel(loadWeather: com.example.weatherapplication.weatherDay.useCases.loadWeather.LoadWeatherUseCase): ViewModel = WeatherDayViewModel(loadWeather)

}