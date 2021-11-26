package com.example.weatherapplication.screens.first.recyclerAdapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.databinding.WeatherItemBinding
import com.example.weatherapplication.screens.first.entities.WeatherWeek
import com.example.weatherapplication.utils.Converter.getDate
import com.example.weatherapplication.utils.Converter.getDay
import com.example.weatherapplication.utils.setPicture

class WeatherWeekViewHolder(private val binding: WeatherItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun setData(weatherForDay: WeatherWeek) {
        val date = binding.date
        date.text = "".getDate(
            weatherForDay.time,
            "dd/MM/yyyy"
        ) + "".getDay(weatherForDay.time)
        val temperature = binding.temperature
        temperature.text = weatherForDay.temp.toString() + "℃"
        val main = binding.main
        main.text = weatherForDay.text
        val pressure = binding.pressure
        pressure.text = "pressure kPa: " + weatherForDay.pressure.toString()
        val wind = binding.wind
        wind.text = "wind m/s :" + weatherForDay.wind
        val image = binding.image
        setPicture(weatherForDay.text,image)

    }
}