package com.example.weatherapplication.di

import com.example.weatherapplication.repository.WeatherRepository
import com.example.weatherapplication.screens.first.recyclerAdapter.WeatherWeekAdapter
import com.example.weatherapplication.screens.second.recyclerAdapter.Weather5DaysAdapter
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideRecyclerWeek(): WeatherWeekAdapter {
        return WeatherWeekAdapter()
    }

    @Provides
    fun provideRecycler5Day(): Weather5DaysAdapter {
        return Weather5DaysAdapter()
    }

    @Provides
    fun provideRepository(): WeatherRepository {
        return WeatherRepository()
    }

}