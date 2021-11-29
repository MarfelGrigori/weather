package com.example.weatherapplication.di

import com.example.weatherapplication.MainActivity
import com.example.weatherapplication.networking.weather.WeatherApi
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [WeatherApi::class,SubcomponentModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
    fun loginComponent(): MainComponent.Factory
}