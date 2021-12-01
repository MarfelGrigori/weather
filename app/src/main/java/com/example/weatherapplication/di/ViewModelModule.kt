package com.example.weatherapplication.di

import androidx.lifecycle.ViewModel
import com.example.myapplicationdagger.di.ViewModelKey
import com.example.weatherapplication.viewModel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel


}