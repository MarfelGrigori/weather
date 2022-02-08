package com.example.weatherapplication.common.di

import com.example.weatherapplication.common.MainActivity
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