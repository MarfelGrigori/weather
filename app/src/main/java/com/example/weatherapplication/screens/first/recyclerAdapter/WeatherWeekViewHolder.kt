package com.example.weatherapplication.screens.first.recyclerAdapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.WeatherItemBinding
import com.example.weatherapplication.screens.first.entities.WeatherWeek
import com.example.weatherapplication.utils.Converter.getDate
import com.example.weatherapplication.utils.Converter.getDay

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
        when (weatherForDay.text) {
            ("Clouds") -> {
                image.setImageResource(R.drawable.cloud)
            }
            ("Rain") -> {
                image.setImageResource(R.drawable.union)
            }
            ("Clear") -> {
                image.setImageResource(R.drawable.sun)
            }
            ("Snow") -> {
                image.setImageResource(R.drawable.snow)
            }
        }
    }
}