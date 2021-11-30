package com.example.weatherapplication.screens.second.recyclerAdapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.databinding.WeatherItemBinding
import com.example.weatherapplication.screens.second.entities.WeatherTo5Days
import com.example.weatherapplication.utils.Converter.getDate
import com.example.weatherapplication.utils.setPicture
import com.example.weatherapplication.utils.toPicture


class Weather5DayViewHolder(private val binding: WeatherItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun setData(weatherForDay: WeatherTo5Days) {
        binding.date.text = weatherForDay.time.getDate("dd/MM/yyyy hh:mm")
        binding.temperature.text = weatherForDay.temp?.toInt().toString() + " ℃"
        binding.main.text = weatherForDay.text
        binding.pressure.text = "pressure kPa: " + weatherForDay.pressure.toString()
        binding.wind.text = "wind m/s :" + weatherForDay.wind
        (weatherForDay.text.toPicture()).setPicture(binding.image)
    }
}
