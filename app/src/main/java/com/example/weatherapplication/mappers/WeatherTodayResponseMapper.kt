package com.example.weatherapplication.mappers


import com.example.weatherapplication.entities.WeatherToday
import com.example.weatherapplication.response.WeatherTodayResponse
import com.example.weatherapplication.utils.Converter
import java.text.Format

class WeatherTodayResponseMapper:Mapper<WeatherTodayResponse, WeatherToday> {
    override fun map(from: WeatherTodayResponse): WeatherToday {
        return WeatherToday(
            city = from.name ?: "",
            country = from.sys?.country ?: "",
            temp = from.main?.temp?.toInt() ?: -50000,
            main = from.weather?.get(0)?.main ?: "",
            humidity = from.main?.humidity ?: -1,
            //< -1.0
            rain = from.rain?.oneH ?: 0.0,
            //>
            snow = from.snow?.oneH ?: -1.0,
            pressure = from.main?.pressure ?: -1,
            speed = from.wind?.speed ?: -1.0,
            //<
            deg = Converter.degToWindRoze(from.wind?.deg ?:-1)
        //>
        )
    }
}