package com.example.weatherapplication.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object Converter {

    fun String.degToWindRoze(deg: Int) =
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

    @SuppressLint("SimpleDateFormat")
    fun String.getDate(millis: Long, dateFormat: String): String {
        val formatter = SimpleDateFormat(dateFormat)
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = millis
        return formatter.format(calendar.time)
    }

    fun String.getDay(mil: Long): String {
        val daysArray =
            arrayOf("Thursday", "Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday")
        val day: Int = ((TimeUnit.MILLISECONDS.toDays(mil) % 7).toInt())
        return daysArray[day]
    }
}