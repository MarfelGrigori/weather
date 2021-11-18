package com.example.weatherapplication

import com.example.weatherapplication.entities.WeatherTo5Days
import com.example.weatherapplication.entities.WeatherToday
import com.example.weatherapplication.mappers.WeatherTo5DaysResponseMapper
import com.example.weatherapplication.mappers.WeatherTodayResponseMapper
import com.example.weatherapplication.repository.WeatherRepository


class LoadWeatherUseCase {

    private val weatherRepository = WeatherRepository()
    private val weatherTodayResponseMapper= WeatherTodayResponseMapper()
    private val weatherTo5DaysResponseMapper = WeatherTo5DaysResponseMapper()

    suspend fun loadWeatherToday(lat:String, lon:String, appid:String): WeatherToday? {
        val response = weatherRepository.loadWeatherToday(lat, lon, appid)
        return if (response.isSuccessful) {
            response.body()?.let {
                weatherTodayResponseMapper.map(it)
            }
        }else {
            throw Throwable(response.errorBody().toString())
        }
    }

    suspend fun loadWeatherTo5Days(lat:String, lon:String, appid:String):List<WeatherTo5Days>?{
        val response = weatherRepository.loadWeatherTo5Days(lat, lon, appid)
        return if(response.isSuccessful){
            response.body()?.let {
                weatherTo5DaysResponseMapper.map(it)
            }
        }else{
            throw Throwable(response.errorBody().toString())
        }
    }
}