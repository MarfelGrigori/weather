package com.example.weatherapplication.screens.common.di

import com.example.weatherapplication.screens.common.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module

abstract class ActivityBuilderModule {

    @ActivityScoped
    @ContributesAndroidInjector(
        modules =
        [
            FragmentBuilderModule::class,
            ViewModelBuilder::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity
}