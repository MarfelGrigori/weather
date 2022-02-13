package com.example.weatherapplication.common.di

import android.app.Application
import com.example.weatherapplication.common.networking.weather.WeatherApi
import com.example.weatherapplication.common.repository.WeatherServer
import com.example.weatherapplication.home.useCase.loadWeather.LoadWeatherUseCase
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuilderModule::class,
        ViewModelModule::class,
        AppModule::class,
        DependenciesModule::class,
        WeatherApi::class,
    ]
)

interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun provideApplication(app: Application): Builder
        fun build(): AppComponent
    }

    fun getNetworkServer(): WeatherServer
    fun getLoadWeatherUseCase(): LoadWeatherUseCase
    fun getLoadWeatherDayUseCase(): com.example.weatherapplication.weatherDay.useCases.loadWeather.LoadWeatherUseCase

}