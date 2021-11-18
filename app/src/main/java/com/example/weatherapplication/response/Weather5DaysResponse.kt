package com.example.weatherapplication.response

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
    ) {
        data class Main(
            @SerializedName("temp")
            val temp: Double?,
        )
        data class Weather(
            @SerializedName("main")
            val main: String?
        )
    }
}