package com.example.weatherapplication.utils

import java.util.concurrent.TimeUnit

object Converter {
    fun millisToHours(mil: Long) = String.format(
        "%02d:%02d",
        TimeUnit.MILLISECONDS.toHours(mil) % 24,
        TimeUnit.MILLISECONDS.toMinutes(mil) % 60
    )

    fun degToWindRoze(deg: Int) =
        when (deg) {
            in 23..67 -> "NE"
            in 68..112 -> "E"
            in 113..157 -> "SE"
            in 158..202 -> "S"
            in 203..247 -> "SW"
            in 248..292 -> "W"
            in 293..337 -> "NW"
            else -> "N"
        }

    fun differenceDays(mil: Long, mil2: Long) = TimeUnit.MILLISECONDS.toDays(mil) != TimeUnit.MILLISECONDS.toDays(mil2)


    fun getDay(mil: Long): String {
        val daysArray =
            arrayOf("Thursday", "Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday")

        val day: Int = ((TimeUnit.MILLISECONDS.toDays(mil) % 7).toInt())

        return daysArray[day]
    }
}