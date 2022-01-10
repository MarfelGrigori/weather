package com.example.weatherapplication.networking.weather

import com.example.weatherapplication.BuildConfig
import com.example.weatherapplication.networking.WeatherService
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
        val interceptor = HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG)
                level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder().baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        return retrofit.create()
    }
}

