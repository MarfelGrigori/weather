package com.example.weatherapplication.screens.home.recyclerAdapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.WeatherItemBinding
import com.example.weatherapplication.screens.home.entities.WeatherWeek
import com.example.weatherapplication.viewModel.MainViewModel
import javax.inject.Inject

class WeatherWeekAdapter @Inject constructor(private val viewModel: MainViewModel) :
    RecyclerView.Adapter<WeatherWeekViewHolder>() {
    private var weatherList = ArrayList<WeatherWeek>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<WeatherWeek>) {
        weatherList = items.toMutableList() as ArrayList<WeatherWeek>
       weatherList.removeAt(weatherList.size-1)
       weatherList.removeAt(weatherList.size-1)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherWeekViewHolder {
        val binding = DataBindingUtil.inflate<WeatherItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.weather_item,
            parent,
            false
        )
        return WeatherWeekViewHolder(binding,viewModel)
    }

    override fun onBindViewHolder(holder: WeatherWeekViewHolder, position: Int) {
        holder.setData(weatherList[position])
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }
}