package com.example.weatherapplication.screens.home.entities

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.WeatherItemBinding
import com.example.weatherapplication.utils.setPicture
import com.example.weatherapplication.utils.toPicture
import com.mikepenz.fastadapter.binding.AbstractBindingItem

data class WeatherWeek(
    val date: String,
    val day : String,
    val text: String,
    val temp: Int,
    val pressure: Int?,
    val wind: Int
) : AbstractBindingItem<WeatherItemBinding>() {

    override val type: Int
        get() = R.id.item_container

    fun withIdentifier(): WeatherWeek {
        this.identifier = this.hashCode().toLong()
        return this
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): WeatherItemBinding {
        return WeatherItemBinding.inflate(inflater, parent, false)
    }

    @SuppressLint("SetTextI18n")
    override fun bindView(binding: WeatherItemBinding, payloads: List<Any>) {
        binding.date.text = date
        binding.day.text = day
        binding.main.text = text
        binding.wind.text = wind.toString()
        binding.pressure.text = pressure.toString()
        binding.temperature.text = temp.toString()
        text.toPicture().setPicture(binding.image)
    }
}

