package com.example.weatherapplication.screens.home.networking

import com.example.weatherapplication.screens.home.entities.WeatherWeek
import com.example.weatherapplication.utils.Converter.getDate
import com.example.weatherapplication.utils.Converter.getDay

fun WeatherWeekResponse.toWeatherWeek (): List<WeatherWeek> {
        val list = ArrayList<WeatherWeek>()
        this.list.forEach {
            list.add(
                WeatherWeek(
                    date = it.dt.times(1000).getDate("dd/MM/yyyy "),
                    text = it.weather[0].main,
                    temp = it.temp.day.toInt(),
                    pressure = it.pressure,
                    wind = it.wind_speed.toInt(),
                    day = it.dt.times(1000).getDay()
                        )
            )
        }
        return list
    }
