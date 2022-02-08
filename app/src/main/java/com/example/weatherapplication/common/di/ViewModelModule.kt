package com.example.weatherapplication.common.di

import androidx.lifecycle.ViewModel
import com.example.weatherapplication.home.viewModel.HomeViewModel
import com.example.weatherapplication.weatherDay.viewModel.WeatherDayViewModel
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