package com.example.weatherapplication.di

import com.example.weatherapplication.presentation.MainActivity
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