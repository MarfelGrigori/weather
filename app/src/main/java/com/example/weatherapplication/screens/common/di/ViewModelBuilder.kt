package com.example.weatherapplication.screens.common.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
internal abstract class ViewModelBuilder {
    @ActivityScoped
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}