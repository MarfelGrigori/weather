package com.example.weatherapplication.mappers

import com.example.weatherapplication.entities.WeatherWeek
import com.example.weatherapplication.response.WeatherWeekResponse

class WeatherWeekResponseMapper : Mapper<WeatherWeekResponse, List<WeatherWeek>> {
    override fun map(from: WeatherWeekResponse): List<WeatherWeek> {
        val list = ArrayList<WeatherWeek>()
        from.list.forEach { list.add(WeatherWeek(
            time = it.dt.times(1000).toString().toLong(),
            text = it.weather[0].main,
            temp = it.temp.day.toInt(),
            pressure = it.pressure,
            wind = it.wind_speed.toInt())) }
   return list
    }

}