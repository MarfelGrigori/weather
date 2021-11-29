package com.example.weatherapplication.screens.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.FragmentFirstBinding
import com.example.weatherapplication.di.AppComponent
import com.example.weatherapplication.di.DaggerAppComponent
import com.example.weatherapplication.screens.first.recyclerAdapter.WeatherWeekAdapter
import com.example.weatherapplication.utils.changeVisibility
import com.example.weatherapplication.utils.setPicture
import com.example.weatherapplication.viewModel.MainViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import javax.inject.Inject

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private val viewModel by activityViewModels<MainViewModel>()
    private lateinit var appComponent : AppComponent
    @Inject
    lateinit var adapter: WeatherWeekAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        appComponent = DaggerAppComponent.create()
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = appComponent.recyclerAdapterWeek
        binding.recyclerView.adapter = adapter
        viewModel.weatherWeek.observe(this) {
            adapter.setItems(it)
        }

        val progressBar = binding.progressBar
        viewModel.isLoading.observe(this) {
            progressBar.changeVisibility(it)
        }
//         надо исправить
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
            setPicture(it, binding.image)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }
    fun checkError(string: String){
        if (string.contains(getString(R.string.error_network_text), true)) {
            MaterialAlertDialogBuilder(requireContext()).setTitle(R.string.error)
                .setMessage(getString(R.string.error_network)).show()
        } else{
            MaterialAlertDialogBuilder(requireContext()).setTitle(R.string.error)
                .setMessage(getString(R.string.something_went_wrong)).show()
        }
    }
}