package com.example.weatherapplication.screens.home.entities

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.weatherapplication.R
import com.example.weatherapplication.utils.Converter.getDate
import com.example.weatherapplication.utils.Converter.getDay
import com.example.weatherapplication.utils.setPicture
import com.example.weatherapplication.utils.toPicture
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

data class WeatherWeek(
    val time: Long,
    val text: String,
    val temp: Int,
    val pressure: Int?,
    val wind: Int) : AbstractItem<WeatherWeek.ViewHolder>() {
    class ViewHolder(view: View) : FastAdapter.ViewHolder<WeatherWeek>(view) {
        var date: TextView = view.findViewById(R.id.date)
        var main: TextView = view.findViewById(R.id.main)
        var wind: TextView = view.findViewById(R.id.wind)
        var pressure: TextView = view.findViewById(R.id.pressure)
        private var temperature: TextView = view.findViewById(R.id.temperature)
        var image: ImageView = view.findViewById(R.id.image_)
        var container: Any? = view.findViewById<ConstraintLayout>(R.id.item_container)
        @SuppressLint("SetTextI18n")
        override fun bindView(item: WeatherWeek, payloads: List<Any>) {
          date.text = item.time.getDate("dd/MM/yyyy") + item.time.getDay()
           main.text = item.text
          wind.text = "wind: ${item.wind.toString()} m/s"
          pressure.text = "pressure ${item.pressure} kPa"
            temperature.text = "${item.temp} °C"
            item.text.toPicture().setPicture(image)

        }

        override fun unbindView(item: WeatherWeek) {
            container = null
        }

    }

    override val type: Int
        get() = R.id.item_container

    override val layoutRes: Int
        get() = R.layout.weather_item

    override fun getViewHolder(v: View): ViewHolder {
       return ViewHolder(v)
    }
}

