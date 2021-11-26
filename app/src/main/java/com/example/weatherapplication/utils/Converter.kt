package com.example.weatherapplication.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object Converter {

    fun Int.degToWindRoze() =
        when (this) {
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
    fun Long.getDate( dateFormat: String): String {
        val formatter = SimpleDateFormat(dateFormat)
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = this
        return formatter.format(calendar.time)
    }
    private val daysArray =
        arrayOf("Thursday", "Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday")

    fun Long.getDay(): String  {
        val day: Int = ((TimeUnit.MILLISECONDS.toDays(this) % 7).toInt())
        return daysArray[day]
    }
}