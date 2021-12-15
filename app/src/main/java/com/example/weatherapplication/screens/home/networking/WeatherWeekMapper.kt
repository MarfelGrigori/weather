package com.example.weatherapplication.screens.home.networking

import com.example.weatherapplication.screens.home.entities.WeatherWeek
import com.example.weatherapplication.screens.home.entities.WeatherWeekWithAllParameters
import com.example.weatherapplication.utils.Converter.getDate
import com.example.weatherapplication.utils.Converter.getDay
import com.example.weatherapplication.utils.toPicture

fun WeatherWeekResponse.toWeatherWeek(): List<WeatherWeek> {
    val list = ArrayList<WeatherWeek>()
    this.list.forEach {
        list.add(
            WeatherWeek(
                data = WeatherWeekWithAllParameters(
                    it.dt.times(1000).getDate("dd/MM/yyyy "),
                    text = it.weather[0].main,
                    temp = it.temp.day.toInt().toString(),
                    pressure = it.pressure.toString(),
                    wind = it.wind_speed.toInt().toString(),
                    day = it.dt.times(1000).getDay(),
                    picture = it.weather[0].main.toPicture()
                )
            )
        )
    }
    return list
}
