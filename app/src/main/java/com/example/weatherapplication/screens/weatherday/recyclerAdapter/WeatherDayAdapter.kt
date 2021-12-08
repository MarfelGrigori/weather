package com.example.weatherapplication.screens.weatherday.recyclerAdapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.WeatherItemBinding
import com.example.weatherapplication.screens.weatherday.entities.WeatherDay
import com.example.weatherapplication.utils.Converter.getDate
import com.example.weatherapplication.viewModel.MainViewModel
import javax.inject.Inject


class WeatherDayAdapter @Inject constructor(private val viewModel: MainViewModel) : RecyclerView.Adapter<WeatherDayViewHolder>() {
    private var weatherList = ArrayList<WeatherDay>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<WeatherDay>) {
        items.forEach { if (it.time.getDate("dd/MM/yyyy")==viewModel.date){
            weatherList.add(it)
        } }
//        weatherList = items.toMutableList() as ArrayList<WeatherDay>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherDayViewHolder {
        val binding = DataBindingUtil.inflate<WeatherItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.weather_item,
            parent,
            false
        )
        return WeatherDayViewHolder(binding,viewModel)
    }

    override fun onBindViewHolder(holder: WeatherDayViewHolder, position: Int) {
        holder.setData(weatherList[position])
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }
}