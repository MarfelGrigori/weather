package com.example.weatherapplication.screens.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil

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
        binding.recyclerView.itemAnimator = null
        viewModel.weatherWeek.observe(viewLifecycleOwner) {
            FastAdapterDiffUtil[itemAdapter] = it
        }

        fastAdapter.onClickListener = { _, _, item, _ ->
          changeFragment(item)
            false
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBar.changeVisibility(it)
        }
        viewModel.errorBus.observe(viewLifecycleOwner) {
            MaterialAlertDialogBuilder(requireContext()).setTitle(it)
                .setMessage(it).show()
        }
        viewModel.temperatureToday.observe(viewLifecycleOwner,binding.temperature::setText)
        viewModel.currentCity.observe(viewLifecycleOwner,binding.city::setText)
        viewModel.currentCountry.observe(viewLifecycleOwner,binding.country::setText)
        viewModel.mainToday.observe(viewLifecycleOwner,binding.main::setText)
        viewModel.mainToday.observe(viewLifecycleOwner){
            (it.toPicture()).setPicture(binding.image)
        }
    }

    private fun changeFragment (item : WeatherWeek){
        val date =item.time.getDate("dd/MM/yyyy")
        val secondFragment = WeatherDayFragment(date)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view_tag, secondFragment)
            .addToBackStack(null)
            .commit()
    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}