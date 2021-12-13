package com.example.weatherapplication.screens.weatherday

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.weatherapplication.databinding.FragmentSecondBinding
import com.example.weatherapplication.di.BaseFragment
import com.example.weatherapplication.screens.weatherday.entities.WeatherDay
import com.example.weatherapplication.utils.Converter.getDate
import com.example.weatherapplication.viewModel.MainViewModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil

class WeatherDayFragment(val date: String) : BaseFragment() {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding
    val viewModel: MainViewModel by activityViewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val neededDate = date
        val itemAdapter = ItemAdapter<WeatherDay>()
        val fastAdapter = FastAdapter.with(itemAdapter)
        binding?.recyclerView?.adapter = fastAdapter
        binding?.recyclerView?.itemAnimator = null
        viewModel.weatherToDay.observe(viewLifecycleOwner) {
          val list =  it.filter {
              it.time.getDate("dd/MM/yyyy").contains(neededDate) }
            FastAdapterDiffUtil[itemAdapter] = list
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}