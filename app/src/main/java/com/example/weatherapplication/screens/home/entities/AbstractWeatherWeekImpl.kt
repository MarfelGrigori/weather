package com.example.weatherapplication.screens.home.entities

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.WeatherItemBinding
import com.example.weatherapplication.utils.setPicture
import com.example.weatherapplication.utils.toPicture
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class AbstractWeatherWeekImpl(private val weatherWeek: WeatherWeek) : AbstractBindingItem<WeatherItemBinding>() {

    override val type: Int
        get() = R.id.item_container

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): WeatherItemBinding {
        return WeatherItemBinding.inflate(inflater, parent, false)
    }

    @SuppressLint("SetTextI18n")
    override fun bindView(binding: WeatherItemBinding, payloads: List<Any>) {
//        binding.date.text = weatherWeek.time.getDate("dd/MM/yyyy") + weatherWeek.time.getDay()
        binding.main.text = weatherWeek.text
        binding.wind.text = "wind: ${weatherWeek.wind}wind m/s"
        binding.pressure.text = "pressure ${weatherWeek.pressure}pressure kPa"
        binding.temperature.text = "${weatherWeek.temp}°C"
        weatherWeek.text.toPicture().setPicture(binding.image)
    }
}