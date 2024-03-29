package com.example.weatherapplication.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.FragmentFirstBinding
import com.example.weatherapplication.common.di.BaseFragment
import com.example.weatherapplication.home.viewModel.HomeViewModel
import com.example.weatherapplication.home.models.WeatherWeek
import com.example.weatherapplication.common.utils.Converter.subscribe
import com.example.weatherapplication.common.utils.changeVisibility
import com.example.weatherapplication.common.utils.setPicture
import com.example.weatherapplication.home.models.WeatherWeekWithAllParameters
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

const val TAG = "message"

class HomeFragment : BaseFragment() {
    private var _binding: FragmentFirstBinding? = null

    private val viewModel: HomeViewModel by activityViewModels { viewModelFactory }

    lateinit var list: List<WeatherWeekWithAllParameters>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemAdapter = ItemAdapter<WeatherWeek>()
        val fastAdapter = FastAdapter.with(itemAdapter)
        _binding?.recyclerView?.adapter = fastAdapter
        _binding?.recyclerView?.itemAnimator = null
        viewModel.weatherWeek.subscribe(lifecycleScope) { it ->
            val items = it.map { WeatherWeek(it) } as MutableList<WeatherWeek>
            try {
                FastAdapterDiffUtil[itemAdapter] = items.subList(1, items.size)
            } catch (e: Exception) {
                Log.e("TAG", "error")
            }
        }
        viewModel.weatherToday.onEach { changeFragment(WeatherWeek(it[0])) }
            .launchIn(lifecycleScope)
        _binding?.head?.setOnClickListener {
            viewModel.gotoWeatherDay()
        }

        fastAdapter.onClickListener = { _, _, item, _ ->
            changeFragment(item)
            false
        }

        viewModel.temperatureToday.subscribe(lifecycleScope, _binding!!.temperature::setText)
        viewModel.isLoading.subscribe(lifecycleScope) { _binding?.progressBar?.changeVisibility(it) }
        viewModel.errorBus.subscribe(lifecycleScope) {
            MaterialAlertDialogBuilder(requireContext()).setTitle(it)
                .setMessage(it).show()
        }
        viewModel.currentCity.subscribe(lifecycleScope, _binding!!.city::setText)
        viewModel.currentCountry.subscribe(lifecycleScope, _binding!!.country::setText)
        viewModel.mainToday.subscribe(lifecycleScope, _binding!!.main::setText)
        viewModel.picture.subscribe(lifecycleScope) {
            it?.setPicture(_binding!!.image)
        }
    }

    private fun changeFragment(item: WeatherWeek) {
        val date = item.data.date
        val bundle = Bundle()
        bundle.putString(TAG, date)
        findNavController().navigate(R.id.action_homeFragment_to_weatherDayFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


