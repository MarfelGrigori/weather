package com.example.weatherapplication.di

import com.example.myapplicationdagger.di.FragmentScoped
import com.example.weatherapplication.screens.first.FirstFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBuilderModule {

    @ContributesAndroidInjector
    @FragmentScoped
    abstract fun contributeBlankFragment(): FirstFragment
}