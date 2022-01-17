package com.example.weatherapplication.screens.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.FragmentFirstBinding
import com.example.weatherapplication.di.BaseFragment
import com.example.weatherapplication.screens.home.entities.WeatherWeek
import com.example.weatherapplication.screens.home.viewmodel.HomeViewModel
import com.example.weatherapplication.screens.weatherday.WeatherDayFragment
import com.example.weatherapplication.screens.weatherday.viewmodel.WeatherDayViewModel
import com.example.weatherapplication.utils.Converter.subscribe
import com.example.weatherapplication.utils.changeVisibility
import com.example.weatherapplication.utils.setPicture
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil

class HomeFragment : BaseFragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by activityViewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemAdapter = ItemAdapter<WeatherWeek>()
        val fastAdapter = FastAdapter.with(itemAdapter)
        binding.recyclerView.adapter = fastAdapter
        binding.recyclerView.itemAnimator = null
        viewModel.weatherWeek.subscribe(lifecycleScope) { it ->
            val items = it.map { WeatherWeek(it) } as MutableList<WeatherWeek>
            binding.head.setOnClickListener { changeFragment(items[0]) }
            try {
                FastAdapterDiffUtil[itemAdapter] = items
            } catch (e: Exception) {
                Log.e("TAG", "error")
            }
        }

        fastAdapter.onClickListener = { _, _, item, _ ->
            changeFragment(item)
            false
        }

        viewModel.temperatureToday.subscribe(lifecycleScope, binding.temperature::setText)
        viewModel.isLoading.subscribe(lifecycleScope) { binding.progressBar.changeVisibility(it) }
        viewModel.errorBus.subscribe(lifecycleScope) {
            MaterialAlertDialogBuilder(requireContext()).setTitle(it)
                .setMessage(it).show()
        }
        viewModel.currentCity.subscribe(lifecycleScope, binding.city::setText)
        viewModel.currentCountry.subscribe(lifecycleScope, binding.country::setText)
        viewModel.mainToday.subscribe(lifecycleScope, binding.main::setText)
        viewModel.picture.subscribe(lifecycleScope) { it?.setPicture(binding.image) }
    }

    private fun changeFragment(item: WeatherWeek) {
        val date = item.data.date
        val bundle = Bundle()
        bundle.putString("message", date)
        val secondFragment = WeatherDayFragment()
        secondFragment.arguments = bundle
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


