package com.example.weatherapplication.recycler_adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.R
import com.example.weatherapplication.entities.WeatherTo5Days
import com.example.weatherapplication.utils.Converter


class Weather5DaysAdapter(val weatherList: List<WeatherTo5Days>) :
    RecyclerView.Adapter<Weather5DaysAdapter.WeatherViewHolder>() {
    inner class WeatherViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun setData(itemView: View, position: Int) {
            val weatherForDay = weatherList[position]
            val date = itemView.findViewById<TextView>(R.id.date)
            date.text = Converter.getDate(weatherForDay.time, "dd/MM/yyyy hh:mm")
            val temperature = itemView.findViewById<TextView>(R.id.temperature)
            temperature.text = weatherForDay.temp?.toInt().toString() + "℃"
            val main = itemView.findViewById<TextView>(R.id.main)
            main.text = weatherForDay.text
            val pressure = itemView.findViewById<TextView>(R.id.pressure)
            pressure.text = "pressure kPa: " + weatherForDay.pressure.toString()
            val wind = itemView.findViewById<TextView>(R.id.wind)
            wind.text = "wind m/s :" + weatherForDay.wind
            val image = itemView.findViewById<ImageView>(R.id.image_)
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_item, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.setData(holder.itemView, position)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }
}