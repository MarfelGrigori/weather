package com.example.weatherapplication.screens.home.entities

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.WeatherItemBinding
import com.example.weatherapplication.utils.Converter.getDate
import com.example.weatherapplication.utils.Converter.getDay
import com.example.weatherapplication.utils.setPicture
import com.example.weatherapplication.utils.toPicture
import com.mikepenz.fastadapter.binding.AbstractBindingItem

data class WeatherWeek(
    val time: Long,
    val text: String,
    val temp: Int,
    val pressure: Int?,
    val wind: Int
) : AbstractBindingItem<WeatherItemBinding>() {

    private val currentTime = time.getDate("dd/MM/yyyy ") + time.getDay()
    override val type: Int
        get() = R.id.item_container

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): WeatherItemBinding {
        return WeatherItemBinding.inflate(inflater, parent, false)
    }

    @SuppressLint("SetTextI18n")
    override fun bindView(binding: WeatherItemBinding, payloads: List<Any>) {
        binding.date.text = currentTime
        binding.main.text = text
        binding.wind.text = wind.toString()
        binding.pressure.text = pressure.toString()
        binding.temperature.text = temp.toString()
        text.toPicture().setPicture(binding.image)
    }
}

