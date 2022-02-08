package com.example.weatherapplication.common.di

import com.example.weatherapplication.home.HomeFragment
import com.example.weatherapplication.weatherDay.WeatherDayFragment
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