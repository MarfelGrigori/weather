package com.example.weatherapplication.screens.first.recyclerAdapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.WeatherItemBinding
import com.example.weatherapplication.screens.first.entities.WeatherWeek

class WeatherWeekAdapter :
    RecyclerView.Adapter<WeatherWeekViewHolder>() {
    private var weatherList = ArrayList<WeatherWeek>()

    @SuppressLint("NotifyDataSetChanged")
    fun initialize(list: List<WeatherWeek>) {
        weatherList = list.toMutableList() as ArrayList<WeatherWeek>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherWeekViewHolder {
        val binding = DataBindingUtil.inflate<WeatherItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.weather_item,
            parent,
            false
        )
        return WeatherWeekViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherWeekViewHolder, position: Int) {
        holder.setData(weatherList[position])
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }
}