package com.example.weatherapplication.entities

data class WeatherTo5Days(
    var newDay:String?=null,
    val time:Long,
    val text:String,
    val temp:Double?,
    val pressure: Int?,
    val wind: Int
)