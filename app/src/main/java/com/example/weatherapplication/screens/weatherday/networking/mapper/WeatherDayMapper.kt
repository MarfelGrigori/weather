package com.example.weatherapplication.screens.weatherday.networking.mapper

import com.example.weatherapplication.screens.weatherday.entities.WeatherDay
import com.example.weatherapplication.screens.weatherday.entities.WeatherDayWithAllParameters
import com.example.weatherapplication.screens.weatherday.networking.response.WeatherDayResponse
import com.example.weatherapplication.utils.Converter.getDate
import com.example.weatherapplication.utils.toPicture

fun WeatherDayResponse.toWeatherDay(): List<WeatherDay> {
    val list = ArrayList<WeatherDay>()
    this.list?.forEach {
        list.add(
            WeatherDay(
                data = WeatherDayWithAllParameters(
                    time = it.dt?.times(1000)?.getDate("dd/MM/yyyy HH:mm").toString(),
                    text = it.weather?.get(0)?.main.toString(),
                    temp = it.main?.temp,
                    newDay = it.dt.toString(),
                    pressure = it.main?.pressure,
                    wind = it.wind.speed.toInt(),
                    picture = it.weather?.get(0)?.main.toString().toPicture()
                )
            )
        )
    }
    return list
}
