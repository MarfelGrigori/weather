package com.example.weatherapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.recycler_adapter.WeatherWeekAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewModel.weatherToday.observe(viewLifecycleOwner){
            val city = view.findViewById<TextView>(R.id.city)
            val main = view.findViewById<TextView>(R.id.main)
            val temperature = view.findViewById<TextView>(R.id.temperature)
            val image = view.findViewById<ImageView>(R.id.image)
            val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
            val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
            viewModel.weatherToday.observe(this) {
                city.text = ("${it.city}, ${it.country}")
                main.text = it.main
                temperature.text = ("${it.temp}°C")
                when (it.main) {
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
                recyclerView.layoutManager = LinearLayoutManager(view.context)

                viewModel.weatherWeek.observe(this) {
                    Log.e("TAG", it.toString())
                    recyclerView.adapter = WeatherWeekAdapter(it)
                }

                viewModel.errorBus.observe(this) {
                    MaterialAlertDialogBuilder(view.context).setTitle("Error").setMessage(it).show()
                }
            }
            viewModel.isLoading.observe(this) {
                if (it) progressBar.visibility = View.VISIBLE
                else progressBar.visibility = View.GONE
            }
        }
    }

}