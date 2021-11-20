package com.example.weatherapplication.recycler_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.R
import com.example.weatherapplication.entities.WeatherTo5Days

class Weather5DaysAdapter(val weatherList: List<WeatherTo5Days>):RecyclerView.Adapter<Weather5DaysAdapter.WeatherViewHolder>() {
    inner class WeatherViewHolder(private val view:View):RecyclerView.ViewHolder(view){
     fun setData(itemView: View,position: Int){
         val weatherForDay = weatherList[position]
         val date = itemView.findViewById<TextView>(R.id.date)
         date.text = weatherForDay.time.toString()
         val temperature = itemView.findViewById<TextView>(R.id.temperature)
         temperature.text = weatherForDay.temp.toString()
         val main = itemView.findViewById<TextView>(R.id.main)

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