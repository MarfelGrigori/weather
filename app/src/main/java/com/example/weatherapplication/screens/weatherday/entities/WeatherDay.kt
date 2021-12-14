package com.example.weatherapplication.screens.weatherday.entities

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.WeatherItemBinding
import com.example.weatherapplication.utils.setPicture
import com.example.weatherapplication.utils.toPicture
import com.mikepenz.fastadapter.binding.AbstractBindingItem

data class WeatherDay(
    var newDay: String? = null,
    val time: String,
    val text: String,
    val temp: Double?,
    val pressure: Int?,
    val wind: Int
): AbstractBindingItem<WeatherItemBinding>()
{

    override val type: Int
        get() = R.id.item_container

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): WeatherItemBinding {
        return WeatherItemBinding.inflate(inflater, parent, false)
    }
    @SuppressLint("SetTextI18n")
    override fun bindView(binding: WeatherItemBinding, payloads: List<Any>) {
        binding.date.text = time
        binding.main.text = text
        binding.wind.text = wind.toString()
        binding.pressure.text = pressure.toString()
        binding.temperature.text = temp?.toInt().toString()
        text.toPicture().setPicture(binding.image)
    }
}