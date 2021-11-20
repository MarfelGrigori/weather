package com.example.weatherapplication.mappers

import com.example.weatherapplication.entities.WeatherTo5Days
import com.example.weatherapplication.response.Weather5DaysResponse


class WeatherTo5DaysResponseMapper : Mapper<Weather5DaysResponse, List<WeatherTo5Days>> {
    override fun map(from: Weather5DaysResponse): List<WeatherTo5Days> {
        val list = ArrayList<WeatherTo5Days>()
        from.list?.forEach {
            list.add(
                WeatherTo5Days(
                    time = it.dt?.times(1000).toString().toLong(),
                    text = it.weather?.get(0)?.main.toString(),
                    temp = it.main?.temp,
                    newDay = it.dt.toString(),
                    pressure = it.main?.pressure,
                    wind = it.main?.wind?.speed
                )
            )
        }
        return list
    }
}