package com.example.weatherapplication.weatherDay.useCase.networking.mapper

import com.example.weatherapplication.weatherDay.useCase.models.WeatherDayWithAllParameters
import com.example.weatherapplication.weatherDay.useCase.networking.response.WeatherDayResponse
import com.example.weatherapplication.common.utils.Converter.getDate
import com.example.weatherapplication.common.utils.toPicture

fun WeatherDayResponse.toWeatherDay(): List<WeatherDayWithAllParameters> {
    val list = ArrayList<WeatherDayWithAllParameters>()
    this.list?.forEach {
        list.add(
            WeatherDayWithAllParameters(
                time = it.dt?.times(1000)?.getDate("dd/MM/yyyy HH:mm").toString(),
                text = it.weather?.get(0)?.main.toString(),
                temp = it.main?.temp,
                newDay = it.dt.toString(),
                pressure = it.main?.pressure,
                wind = it.wind.speed.toInt(),
                picture = it.weather?.get(0)?.main.toString().toPicture()
            )
        )
    }
    return list
}
