package com.example.weatherapplication.recycler_adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.R
import com.example.weatherapplication.entities.WeatherWeek
import java.text.SimpleDateFormat
import java.util.*

class WeatherWeekAdapter(val weatherList: List<WeatherWeek>): RecyclerView.Adapter<WeatherWeekAdapter.WeatherWeekViewHolder>() {
    inner class WeatherWeekViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        fun setData(itemView: View, position: Int){
            val weatherForDay = weatherList[position]
            val container = itemView.findViewById<ConstraintLayout>(R.id.item_container)

            val date = itemView.findViewById<TextView>(R.id.date)
            date.text = getDate(weatherForDay.time,"dd/MM/yyyy")
            val temperature = itemView.findViewById<TextView>(R.id.temperature)
            temperature.text = weatherForDay.temp?.toInt().toString()+"℃"
            val main = itemView.findViewById<TextView>(R.id.main)
            main.text = weatherForDay.text
            val pressure = itemView.findViewById<TextView>(R.id.pressure)
            pressure.text = "pressure kPa: "+weatherForDay.pressure.toString()
            val wind = itemView.findViewById<TextView>(R.id.wind)
            wind.text = "wind m/s :"+ weatherForDay.wind
            val image = itemView.findViewById<ImageView>(R.id.image_)
            when(weatherForDay.text){
                ("Clouds")-> {image.setImageResource(R.drawable.cloud)}
                ("Rain")-> {image.setImageResource(R.drawable.union)}
                ("Clear")->{image.setImageResource(R.drawable.sun)}
                ("Snow")->{image.setImageResource(R.drawable.snow)}
            }
        }
        @SuppressLint("SimpleDateFormat")
        fun getDate(millis: Long, dateFormat: String): String {
            val formatter = SimpleDateFormat(dateFormat)
            val calendar: Calendar = Calendar.getInstance()
            calendar.timeInMillis = millis
            return formatter.format(calendar.time)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherWeekViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_item, parent, false)
        return WeatherWeekViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherWeekViewHolder, position: Int) {
        holder.setData(holder.itemView, position)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }
}