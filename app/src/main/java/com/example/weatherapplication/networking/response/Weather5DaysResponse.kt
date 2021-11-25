package com.example.weatherapplication.networking.response

import com.example.weatherapplication.entities.WeatherTo5Days
import com.google.gson.annotations.SerializedName

data class Weather5DaysResponse(
    @SerializedName("list")
    val list: List<Weathers>?,
) {

    data class Weathers(
        @SerializedName("dt")
        val dt: Long?,
        @SerializedName("main")
        val main: Main?,
        @SerializedName("visibility")
        val visibility: Int?,
        @SerializedName("weather")
        val weather: List<Weather?>?,
        @SerializedName("wind")
        val wind: Wind
    ) {
        data class Main(
            @SerializedName("temp")
            val temp: Double?,
            @SerializedName("pressure")
            val pressure: Int?,
        )

        data class Weather(
            @SerializedName("main")
            val main: String?
        )

        data class Wind(
            @SerializedName("speed")
            val speed: Double
        )
    }
    companion object{

    }
}