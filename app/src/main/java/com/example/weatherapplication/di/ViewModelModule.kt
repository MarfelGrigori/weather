package com.example.weatherapplication.di

import androidx.lifecycle.ViewModel
import com.example.weatherapplication.screens.home.viewmodel.HomeViewModel
import com.example.weatherapplication.screens.weatherday.viewmodel.WeatherDayViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: HomeViewModel): ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(WeatherDayViewModel::class)
    abstract fun bindSecondViewModel(mainViewModel: WeatherDayViewModel): ViewModel

}