package com.example.weatherapplication.screens.weatherday.networking.mapper

import com.example.weatherapplication.screens.weatherday.entities.WeatherDay
import com.example.weatherapplication.screens.weatherday.networking.response.WeatherDayResponse

fun WeatherDayResponse.toWeatherDay(): List<WeatherDay> {
    val list = ArrayList<WeatherDay>()
    this.list?.forEach {
        list.add(
            WeatherDay(
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
