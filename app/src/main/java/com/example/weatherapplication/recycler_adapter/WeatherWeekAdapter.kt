package com.example.weatherapplication.recycler_adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.WeatherItemBinding
import com.example.weatherapplication.entities.WeatherWeek
import com.example.weatherapplication.utils.Converter.getDate
import com.example.weatherapplication.utils.Converter.getDay

class WeatherWeekAdapter :
    RecyclerView.Adapter<WeatherWeekAdapter.WeatherWeekViewHolder>() {
    private var weatherList = ArrayList<WeatherWeek>()
    fun initialize(list: List<WeatherWeek>) {
        weatherList = list.toMutableList() as ArrayList<WeatherWeek>
        notifyDataSetChanged()
    }
    inner class WeatherWeekViewHolder(private val binding:WeatherItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun setData(weatherForDay: WeatherWeek) {
            val date = binding.date
            date.text = "".getDate(
                weatherForDay.time,
                "dd/MM/yyyy"
            ) + "".getDay(weatherForDay.time)
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
        holder.setData(weatherList[position])
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }
}