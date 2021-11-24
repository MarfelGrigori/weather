package com.example.weatherapplication

import com.example.weatherapplication.entities.WeatherTo5Days
import com.example.weatherapplication.entities.WeatherToday
import com.example.weatherapplication.entities.WeatherWeek
import com.example.weatherapplication.repository.WeatherRepository
import com.example.weatherapplication.response.Weather5DaysResponse.Companion.toWeather5day
import com.example.weatherapplication.response.WeatherTodayResponse.Companion.toWeatherToday
import com.example.weatherapplication.response.WeatherWeekResponse.Companion.toWeatherWeek


class LoadWeatherUseCase {

    private val weatherRepository = WeatherRepository()


    suspend fun loadWeatherToday(lat: String, lon: String, appid: String): WeatherToday? {

        val response = weatherRepository.loadWeatherToday(lat, lon, appid)
        return if (response.isSuccessful) {
            response.body()?.toWeatherToday()
        } else {
            throw Throwable(response.errorBody().toString())
        }
    }

    suspend fun loadWeatherTo5Days(lat: String, lon: String, appid: String): List<WeatherTo5Days>? {
        val response = weatherRepository.loadWeatherTo5Days(lat, lon, appid)
        return if (response.isSuccessful) {
            response.body()?.let {
               it.toWeather5day(it)
            }
        } else {
            throw Throwable(response.errorBody().toString())
        }
    }

    suspend fun loadWeatherWeek(lat: String, lon: String, appid: String): List<WeatherWeek>? {
        val response = weatherRepository.loadWeatherWeek(lat, lon, appid)
        return if (response.isSuccessful) {
            response.body()?.let {
                it.toWeatherWeek(it)
            }
        } else {
            throw Throwable(response.errorBody().toString())
        }
    }


}