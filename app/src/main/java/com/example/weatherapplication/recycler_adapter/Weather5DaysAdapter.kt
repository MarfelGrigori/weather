package com.example.weatherapplication.recycler_adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.WeatherItemBinding
import com.example.weatherapplication.entities.WeatherTo5Days
import com.example.weatherapplication.utils.Converter.getDate


class Weather5DaysAdapter() :
    RecyclerView.Adapter<Weather5DaysAdapter.WeatherViewHolder>() {
    private var weatherList= ArrayList<WeatherTo5Days>()
    fun initialize(list: List<WeatherTo5Days>) {
        weatherList = list.toMutableList() as ArrayList<WeatherTo5Days>
        notifyDataSetChanged()
    }
    inner class WeatherViewHolder(val binding:WeatherItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun setData(weatherForDay: WeatherTo5Days) {
            val date = binding.date
            date.text = "".getDate(weatherForDay.time, "dd/MM/yyyy hh:mm")
            val temperature = binding.temperature
            temperature.text = weatherForDay.temp?.toInt().toString() + " ℃"
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = DataBindingUtil.inflate<WeatherItemBinding>(LayoutInflater.from(parent.context),R.layout.weather_item,parent,false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.setData(weatherList[position])
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }
}