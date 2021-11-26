package com.example.weatherapplication.screens.second.recyclerAdapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.databinding.WeatherItemBinding
import com.example.weatherapplication.screens.second.entities.WeatherTo5Days
import com.example.weatherapplication.utils.Converter.getDate
import com.example.weatherapplication.utils.setPicture


 class Weather5DayViewHolder(private val binding: WeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun setData(weatherForDay: WeatherTo5Days) {
            val date = binding.date
            date.text = weatherForDay.time.getDate( "dd/MM/yyyy hh:mm")
            val temperature = binding.temperature
            temperature.text = weatherForDay.temp?.toInt().toString() + " ℃"
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
