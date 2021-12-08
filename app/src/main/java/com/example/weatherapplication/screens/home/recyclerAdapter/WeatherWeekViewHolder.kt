package com.example.weatherapplication.screens.home.recyclerAdapter

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.WeatherItemBinding
import com.example.weatherapplication.screens.home.entities.WeatherWeek
import com.example.weatherapplication.screens.weatherday.WeatherDayFragment
import com.example.weatherapplication.utils.Converter.getDate
import com.example.weatherapplication.utils.Converter.getDay
import com.example.weatherapplication.utils.setPicture
import com.example.weatherapplication.utils.toPicture
import com.example.weatherapplication.viewModel.MainViewModel

class WeatherWeekViewHolder(private val binding: WeatherItemBinding,private val viewModel: MainViewModel) :
    RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun setData(weatherForDay: WeatherWeek) {
        binding.date.text = weatherForDay.time.getDate(
            "dd/MM/yyyy"
        ) + weatherForDay.time.getDay()
        binding.temperature.text = weatherForDay.temp.toString() + "℃"
        binding.main.text = weatherForDay.text
        binding.pressure.text = "pressure kPa: " + weatherForDay.pressure.toString()
        binding.wind.text = "wind m/s :" + weatherForDay.wind
        (weatherForDay.text.toPicture()).setPicture(binding.image)
        binding.itemContainer.setOnClickListener {
            val fragmentForDay = WeatherDayFragment()
            viewModel.date = weatherForDay.time.getDate("dd/MM/yyyy")
            var activity: AppCompatActivity = binding.root.context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view_tag,WeatherDayFragment(),"tag")
                .addToBackStack(null)
                .commit()
            val fragmentManager = fragmentForDay.activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container_view_tag,fragmentForDay,"tag")
            fragmentTransaction?.commit()

        }
    }
}