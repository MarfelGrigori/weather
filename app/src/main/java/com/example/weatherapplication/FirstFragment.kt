package com.example.weatherapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapplication.databinding.FragmentFirstBinding
import com.example.weatherapplication.recycler_adapter.WeatherWeekAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class FirstFragment : Fragment() {
    lateinit var binding: FragmentFirstBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        val viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        val city = binding.city
        val main = binding.main
        val temperature = binding.temperature
        val image = binding.image
        val recyclerView = binding.recyclerView
        val adapter = WeatherWeekAdapter()
        recyclerView.adapter = adapter
        viewModel.weatherWeek.observe(this) {
            adapter.initialize(it)
        }
        val progressBar = binding.progressBar

            viewModel.errorBus.observe(this) {
                view?.context?.let { it1 ->
                    MaterialAlertDialogBuilder(it1).setTitle("Error").setMessage(it).show()
                }
            }
            viewModel.isLoading.observe(this) {
                if (it) progressBar.visibility = View.VISIBLE
                else progressBar.visibility = View.GONE
            }

            viewModel.temperatureToday.observe(this){temperature.text = ("${it}°C")}
            viewModel.currentCity.observe(this){
                it1->
                viewModel.currentCountry.observe(this){
                    it2->
                    city.text = ("${it1}, $it2")
                }
            }
            viewModel.mainToday.observe(this){
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
        return binding.root
    }


}