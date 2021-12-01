package com.example.weatherapplication.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Named


@Module

class AppModule {

    @Provides
    fun provideContext(app: Application): Context {
        return app.applicationContext
    }

}