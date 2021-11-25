package com.example.weatherapplication.screens.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.viewModel.MainViewModel
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.FragmentFirstBinding
import com.example.weatherapplication.screens.first.recyclerAdapter.WeatherWeekAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class FirstFragment : Fragment() {
    lateinit var binding: FragmentFirstBinding
    lateinit var viewModel: MainViewModel
    private lateinit var city: TextView
    lateinit var main: TextView
    private lateinit var temperature: TextView
    private lateinit var image: ImageView
    private lateinit var recyclerView: RecyclerView
    lateinit var adapter: WeatherWeekAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        city = binding.city
        main = binding.main
        temperature = binding.temperature
        image = binding.image
        recyclerView = binding.recyclerView
        adapter = WeatherWeekAdapter()
        recyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.weatherWeek.observe(this) {
            adapter.initialize(it)
        }
        val progressBar = binding.progressBar
        viewModel.isLoading.observe(this) {
            if (it == true) progressBar.visibility = View.VISIBLE
            else progressBar.visibility = View.GONE
        }
        viewModel.errorBus.observe(this) {
                MaterialAlertDialogBuilder(view.context).setTitle("Error").setMessage(it).show()
        }
        viewModel.temperatureToday.observe(this) { temperature.text = ("${it}°C") }
        viewModel.currentCity.observe(this) { it1 ->
            viewModel.currentCountry.observe(this) { it2 ->
                city.text = ("${it1}, $it2")
            }
        }
        viewModel.mainToday.observe(this) {
            main.text = it
            when (it) {
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
    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }


}