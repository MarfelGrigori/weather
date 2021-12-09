package com.example.weatherapplication.screens.weatherday.recyclerAdapter

import android.annotation.SuppressLint
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.databinding.WeatherItemBinding
import com.example.weatherapplication.screens.weatherday.entities.WeatherDay
import com.example.weatherapplication.utils.Converter.getDate
import com.example.weatherapplication.utils.setPicture
import com.example.weatherapplication.utils.toPicture
import com.example.weatherapplication.viewModel.MainViewModel


class WeatherDayViewHolder(private val binding: WeatherItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun setData(weatherForDay: WeatherDay) {
        binding.date.text = weatherForDay.time.getDate("dd/MM/yyyy hh:mm")
        binding.temperature.text = weatherForDay.temp?.toInt().toString() + " ℃"
        binding.main.text = weatherForDay.text
        binding.pressure.text = "pressure kPa: " + weatherForDay.pressure.toString()
        binding.wind.text = "wind m/s :" + weatherForDay.wind
        (weatherForDay.text.toPicture()).setPicture(binding.image)
    }
}
