package com.example.weatherapplication.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.FragmentFirstBinding
import com.example.weatherapplication.di.BaseFragment
import com.example.weatherapplication.screens.home.entities.WeatherWeek
import com.example.weatherapplication.screens.weatherday.WeatherDayFragment
import com.example.weatherapplication.utils.Converter.getDate
import com.example.weatherapplication.utils.changeVisibility
import com.example.weatherapplication.utils.setPicture
import com.example.weatherapplication.utils.toPicture
import com.example.weatherapplication.viewModel.MainViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class HomeFragment : BaseFragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    val viewModel: MainViewModel by activityViewModels { viewModelFactory }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemAdapter = ItemAdapter<WeatherWeek>()
        val fastAdapter = FastAdapter.with(itemAdapter)
        binding.recyclerView.adapter = fastAdapter
        viewModel.weatherWeek.observe(this) {
            itemAdapter.set(it)
        }
        fastAdapter.onClickListener = { _, _, item, _ ->
            // Handle click here
            viewModel.date = item.time.getDate("dd/MM/yyyy")
            val fragmentForDay = WeatherDayFragment()
            val activity: AppCompatActivity = binding.root.context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view_tag, WeatherDayFragment(),"tag")
                .addToBackStack(null)
                .commit()
            val fragmentManager = fragmentForDay.activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container_view_tag,fragmentForDay,"tag")
            fragmentTransaction?.commit()
            false
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