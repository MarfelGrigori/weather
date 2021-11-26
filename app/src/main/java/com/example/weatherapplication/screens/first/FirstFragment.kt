package com.example.weatherapplication.screens.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.viewModel.MainViewModel
import com.example.weatherapplication.databinding.FragmentFirstBinding
import com.example.weatherapplication.screens.first.recyclerAdapter.WeatherWeekAdapter
import com.example.weatherapplication.utils.changeVisibility
import com.example.weatherapplication.utils.setPicture
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class FirstFragment : Fragment() {
    lateinit var binding: FragmentFirstBinding
    private lateinit var city: TextView
    private lateinit var country: TextView
    lateinit var main: TextView
    private val viewModel by activityViewModels<MainViewModel>()
    private lateinit var temperature: TextView
    private lateinit var image: ImageView
    private lateinit var recyclerView: RecyclerView
    lateinit var adapter: WeatherWeekAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        city = binding.city
        main = binding.main
        temperature = binding.temperature
        image = binding.image
        recyclerView = binding.recyclerView
        adapter = WeatherWeekAdapter()
        recyclerView.adapter = adapter
        country = binding.country

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.weatherWeek.observe(this) {
            adapter.setItems(it)
        }

        val progressBar = binding.progressBar
        viewModel.isLoading.observe(this) {
            progressBar.changeVisibility(it)
        }
//         надо исправить
        viewModel.errorBus.observe(this) {
                MaterialAlertDialogBuilder(requireContext()).setTitle("Error").setMessage(it).show()
        }
        viewModel.temperatureToday.observe(this) { temperature.text = it.toString() }
        viewModel.currentCity.observe(this) {
            city.text = it.toString()
        }
        viewModel.currentCountry.observe(this) {
            country.text = it
        }
        viewModel.mainToday.observe(this) {
            main.text = it
            setPicture(it, image)
//            when (it) {
//                (Picture.CLOUDS.name) -> {
//                    image.setImageResource(R.drawable.cloud)
//                }
//                (Picture.RAIN.name) -> {
//                    image.setImageResource(R.drawable.union)
//                }
//                (Picture.CLEAR.name) -> {
//                    image.setImageResource(R.drawable.sun)
//                }
//                (Picture.SNOW.name) -> {
//                    image.setImageResource(R.drawable.snow)
//                }
//            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }


}