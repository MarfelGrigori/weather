package com.example.weatherapplication.di

import com.example.weatherapplication.presentation.screens.home.HomeFragment
import com.example.weatherapplication.presentation.screens.weatherDay.WeatherDayFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBuilderModule {

    @ContributesAndroidInjector
    @FragmentScoped
    abstract fun contributeBlankFragment(): HomeFragment

    @ContributesAndroidInjector
    @FragmentScoped
    abstract fun contributeSecondFragment(): WeatherDayFragment

}