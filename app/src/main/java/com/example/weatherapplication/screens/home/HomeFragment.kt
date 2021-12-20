package com.example.weatherapplication.screens.home

import android.os.Bundle
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
import com.example.weatherapplication.utils.changeVisibility
import com.example.weatherapplication.utils.setPicture
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeFragment : BaseFragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding

    private val viewModel: HomeViewModel by activityViewModels { viewModelFactory }
    private val viewModel1: WeatherDayViewModel by activityViewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemAdapter = ItemAdapter<WeatherWeek>()
        val fastAdapter = FastAdapter.with(itemAdapter)
        binding?.recyclerView?.adapter = fastAdapter
        binding?.recyclerView?.itemAnimator = null
        viewModel.weatherWeek.onEach {
            val items = it?.map { WeatherWeek(it) } as MutableList<WeatherWeek>
            binding?.head?.setOnClickListener { changeFragment(items[0]) }
            try {
                val list = items.subList(1,items.size-1)
                FastAdapterDiffUtil[itemAdapter] = list
            } catch (e: Exception){}


        }
            .launchIn(lifecycleScope)

        fastAdapter.onClickListener = { _, _, item, _ ->
            changeFragment(item)
            false
        }
        viewModel.isLoading.onEach {
            it?.let { it1 -> binding?.progressBar?.changeVisibility(it1) }
        }
            .launchIn(lifecycleScope)
        viewModel.errorBus.onEach {
            MaterialAlertDialogBuilder(requireContext()).setTitle(it)
                .setMessage(it).show()
        }
            .launchIn(lifecycleScope)
        viewModel.temperatureToday.onEach { temperature-> binding?.temperature?.text= temperature}
            .launchIn(lifecycleScope)
        viewModel.currentCity.onEach {city-> binding?.city?.text = city }
            .launchIn(lifecycleScope)
        viewModel.currentCountry.onEach { binding?.country?.text = it }
            .launchIn(lifecycleScope)
        viewModel.mainToday.onEach { main -> binding?.main?.text = main }
            .launchIn(lifecycleScope)
        viewModel.picture.onEach {
            binding?.image?.let { it1 -> it?.setPicture(it1) }
        }
            .launchIn(lifecycleScope)
    }

    private fun changeFragment(item: WeatherWeek) {
        val date = item.data.date
        viewModel1.date = date
        val secondFragment = WeatherDayFragment()
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


