package com.example.weatherapplication.screens.second.networking.mapper

import com.example.weatherapplication.screens.second.entities.WeatherTo5Days
import com.example.weatherapplication.screens.second.networking.response.Weather5DaysResponse

object Weather5DayMapper {
    fun Weather5DaysResponse.toWeather5day(): List<WeatherTo5Days>{
        val list = ArrayList<WeatherTo5Days>()
        this.list?.forEach{
            list.add(
                WeatherTo5Days(
                    time = it.dt?.times(1000).toString().toLong(),
                    text = it.weather?.get(0)?.main.toString(),
                    temp = it.main?.temp,
                    newDay = it.dt.toString(),
                    pressure = it.main?.pressure,
                    wind = it.wind.speed.toInt()
                )
            )
        }
        return list
    }
}