package com.example.weatherapplication.screens.first.networking

import com.example.weatherapplication.screens.first.entities.WeatherWeek

    fun WeatherWeekResponse.toWeatherWeek (): List<WeatherWeek> {
        val list = ArrayList<WeatherWeek>()
        this.list.forEach {
            list.add(
                WeatherWeek(
                    time = it.dt.times(1000).toString().toLong(),
                    text = it.weather[0].main,
                    temp = it.temp.day.toInt(),
                    pressure = it.pressure,
                    wind = it.wind_speed.toInt()
                )
            )
        }
        return list
    }
