package com.example.weatherapplication.screens.home.useCase.networking

import com.example.weatherapplication.screens.home.useCase.entities.WeatherWeekWithAllParameters
import com.example.weatherapplication.screens.common.utils.Converter.getDate
import com.example.weatherapplication.screens.common.utils.Converter.getDay
import com.example.weatherapplication.screens.common.utils.toPicture

fun WeatherWeekResponse.toWeatherWeek(): List<WeatherWeekWithAllParameters> {
    val list = ArrayList<WeatherWeekWithAllParameters>()
    this.list.forEach {
        list.add(
            WeatherWeekWithAllParameters(
                it.dt.times(1000).getDate("dd/MM/yyyy "),
                text = it.weather[0].main,
                temp = it.temp.day.toInt().toString(),
                pressure = it.pressure.toString(),
                wind = it.wind_speed.toInt().toString(),
                day = it.dt.times(1000).getDay(),
                picture = it.weather[0].main.toPicture()
            )
        )
    }
    return list
}
