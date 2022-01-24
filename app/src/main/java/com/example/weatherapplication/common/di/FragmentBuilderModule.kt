package com.example.weatherapplication.common.di

import com.example.weatherapplication.home.HostFragment
import com.example.weatherapplication.weatherDay.SecondFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBuilderModule {

    @ContributesAndroidInjector
    @FragmentScoped
    abstract fun contributeBlankFragment(): HostFragment

    @ContributesAndroidInjector
    @FragmentScoped
    abstract fun contributeSecondFragment(): SecondFragment

}