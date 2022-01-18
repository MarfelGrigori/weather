package com.example.weatherapplication.screens.common.networking.weather


import com.example.weatherapplication.screens.common.networking.WeatherService
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Inject
import javax.inject.Singleton

@Module
class WeatherApi @Inject constructor() {
    @Singleton
    @Provides
    fun provideRetrofit(): WeatherService {
        val retrofit = Retrofit.Builder().baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        return retrofit.create()
    }
}

