package com.example.weatherapplication.di

import com.example.weatherapplication.screens.home.FirstFragment
import com.example.weatherapplication.screens.weatherday.WeatherDayFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBuilderModule {

    @ContributesAndroidInjector
    @FragmentScoped
    abstract fun contributeBlankFragment(): FirstFragment
    @ContributesAndroidInjector
    @FragmentScoped
    abstract fun contributeSecondFragment(): WeatherDayFragment

}