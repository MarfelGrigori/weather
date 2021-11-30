package com.example.weatherapplication.screens.first.recyclerAdapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.databinding.WeatherItemBinding
import com.example.weatherapplication.screens.first.entities.WeatherWeek
import com.example.weatherapplication.utils.Converter.getDate
import com.example.weatherapplication.utils.Converter.getDay
import com.example.weatherapplication.utils.setPicture
import com.example.weatherapplication.utils.toPicture

class WeatherWeekViewHolder(private val binding: WeatherItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun setData(weatherForDay: WeatherWeek) {
        binding.date.text = weatherForDay.time.getDate(
            "dd/MM/yyyy"
        ) + weatherForDay.time.getDay()
        binding.temperature.text = weatherForDay.temp.toString() + "℃"
        binding.main.text = weatherForDay.text
        binding.pressure.text = "pressure kPa: " + weatherForDay.pressure.toString()
        binding.wind.text = "wind m/s :" + weatherForDay.wind
        (weatherForDay.text.toPicture()).setPicture(binding.image)
    }
}