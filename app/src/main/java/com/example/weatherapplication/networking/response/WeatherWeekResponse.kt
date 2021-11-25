package com.example.weatherapplication.networking.response

import com.example.weatherapplication.entities.WeatherWeek
import com.google.gson.annotations.SerializedName

data class WeatherWeekResponse(
    @SerializedName("daily")
    val list: List<Daily>,
) {
    data class Daily(
        val clouds: Int,
        val dew_point: Double,
        @SerializedName("dt")
        val dt: Long,
        @SerializedName("pressure")
        val pressure: Int,
        @SerializedName("temp")
        val temp: Temp,
        @SerializedName("weather")
        val weather: List<Weather>,
        @SerializedName("wind_speed")
        val wind_speed: Double
    ) {

        data class Temp(
            @SerializedName("day")
            val day: Double,
        )

        data class Weather(
            @SerializedName("main")
            val main: String
        )
    }
    companion object{
}

}