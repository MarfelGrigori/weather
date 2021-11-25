package com.example.weatherapplication.response

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
        fun Weather5DaysResponse.toWeather5day(from: Weather5DaysResponse): List<WeatherTo5Days>{
            val list = ArrayList<WeatherTo5Days>()
            from.list?.forEach{
                list.add(
                    WeatherTo5Days(
                        time = it.dt?.times(1000).toString().toLong(),
                        text = it.weather?.get(0)?.main.toString(),
                        temp = it.main?.temp,
                        newDay = it.dt.toString(),
                        pressure = it.main?.pressure,
                        wind = it.wind.speed.toInt()
                    )
                )
            }
            return list
        }
    }
}