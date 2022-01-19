package com.example.weatherapplication.home.models

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.WeatherItemBinding
import com.example.weatherapplication.common.utils.setPicture
import com.mikepenz.fastadapter.binding.AbstractBindingItem

data class WeatherWeek(
    val data: WeatherWeekWithAllParameters
) : AbstractBindingItem<WeatherItemBinding>() {

    override val type: Int
        get() = R.id.item_container

    override var identifier: Long
        get() = data.hashCode().toLong()
        set(value) {}

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): WeatherItemBinding {
        return WeatherItemBinding.inflate(inflater, parent, false)
    }

    @SuppressLint("SetTextI18n")
    override fun bindView(binding: WeatherItemBinding, payloads: List<Any>) {
        binding.date.text = data.date
        binding.day.text = data.day
        binding.main.text = data.text
        binding.wind.text = data.wind
        binding.pressure.text = data.pressure
        binding.temperature.text = data.temp
        data.picture.setPicture(binding.image)
    }
}

