package com.example.weatherapplication.di

import com.example.weatherapplication.repository.WeatherRepository
import com.example.weatherapplication.screens.first.recyclerAdapter.WeatherWeekAdapter
import com.example.weatherapplication.screens.second.recyclerAdapter.Weather5DaysAdapter
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    val recyclerAdapterWeek : WeatherWeekAdapter
    val recyclerAdapter5Day : Weather5DaysAdapter
    val repository : WeatherRepository
}