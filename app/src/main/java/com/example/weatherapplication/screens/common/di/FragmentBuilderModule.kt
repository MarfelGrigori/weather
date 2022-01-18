package com.example.weatherapplication.screens.common.di

import com.example.weatherapplication.screens.home.HomeFragment
import com.example.weatherapplication.screens.weatherDay.WeatherDayFragment
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