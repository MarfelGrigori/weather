package com.example.weatherapplication.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.FragmentFirstBinding
import com.example.weatherapplication.di.BaseFragment
import com.example.weatherapplication.di.activityViewModelProvider
import com.example.weatherapplication.screens.home.recyclerAdapter.WeatherWeekAdapter
import com.example.weatherapplication.utils.changeVisibility
import com.example.weatherapplication.utils.setPicture
import com.example.weatherapplication.utils.toPicture
import com.example.weatherapplication.viewModel.MainViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class FirstFragment : BaseFragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: MainViewModel

    lateinit var adapter: WeatherWeekAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        viewModel = activityViewModelProvider()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = WeatherWeekAdapter()
        binding.recyclerView.adapter = adapter
        viewModel.weatherWeek.observe(this) {
            adapter.setItems(it)
        }

        val progressBar = binding.progressBar
        viewModel.isLoading.observe(this) {
            progressBar.changeVisibility(it)
        }
        viewModel.errorBus.observe(this) {
            checkError(it)
        }
        viewModel.temperatureToday.observe(this) { binding.temperature.text = it.toString() }
        viewModel.currentCity.observe(this) {
            binding.city.text = it.toString()
        }
        viewModel.currentCountry.observe(this) {
            binding.country.text = it
        }
        viewModel.mainToday.observe(this) {
            binding.main.text = it
            (it.toPicture()).setPicture(binding.image)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkError(string: String) {
        if (string.contains(getString(R.string.error_network_text), true)) {
            MaterialAlertDialogBuilder(requireContext()).setTitle(R.string.error)
                .setMessage(getString(R.string.error_network)).show()
        } else {
            MaterialAlertDialogBuilder(requireContext()).setTitle(R.string.error)
                .setMessage(getString(R.string.something_went_wrong)).show()
        }
    }
}