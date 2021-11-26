package com.example.weatherapplication.screens.second.recyclerAdapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.WeatherItemBinding
import com.example.weatherapplication.screens.second.entities.WeatherTo5Days


class Weather5DaysAdapter : RecyclerView.Adapter<Weather5DayViewHolder>() {
    private var weatherList = ArrayList<WeatherTo5Days>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<WeatherTo5Days>) {
        weatherList = items.toMutableList() as ArrayList<WeatherTo5Days>
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Weather5DayViewHolder {
        val binding = DataBindingUtil.inflate<WeatherItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.weather_item,
            parent,
            false
        )
        return Weather5DayViewHolder(binding)
    }

    override fun onBindViewHolder(holder: Weather5DayViewHolder, position: Int) {
        holder.setData(weatherList[position])
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }
}