package com.example.weatherapplication.di

import androidx.lifecycle.ViewModel
import com.example.weatherapplication.viewModel.MainViewModel
import com.example.weatherapplication.viewModel.SecondViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(SecondViewModel::class)
    abstract fun bindSecondViewModel(mainViewModel: SecondViewModel): ViewModel

}