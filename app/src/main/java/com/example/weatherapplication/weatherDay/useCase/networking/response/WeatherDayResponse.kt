package com.example.weatherapplication.weatherDay.useCase.networking.response

import com.google.gson.annotations.SerializedName

data class WeatherDayResponse(
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
            @SerializedName("description")
            val main: String?
        )

        data class Wind(
            @SerializedName("speed")
            val speed: Double
        )
    }
}