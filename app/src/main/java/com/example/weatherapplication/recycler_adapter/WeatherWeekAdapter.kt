package com.example.weatherapplication.recycler_adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.WeatherItemBinding
import com.example.weatherapplication.entities.WeatherWeek
import com.example.weatherapplication.utils.Converter

class WeatherWeekAdapter(val weatherList: List<WeatherWeek>) :
    RecyclerView.Adapter<WeatherWeekAdapter.WeatherWeekViewHolder>() {
    inner class WeatherWeekViewHolder(private val binding:WeatherItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun setData(itemView: View, position: Int) {
            val weatherForDay = weatherList[position]
            val date = binding.date
            date.text = Converter.getDate(
                weatherForDay.time,
                "dd/MM/yyyy"
            ) + Converter.getDay(weatherForDay.time)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherWeekViewHolder {
        val binding = DataBindingUtil.inflate<WeatherItemBinding>(LayoutInflater.from(parent.context),R.layout.weather_item,parent,false)
        return WeatherWeekViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherWeekViewHolder, position: Int) {
        holder.setData(holder.itemView, position)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }
}