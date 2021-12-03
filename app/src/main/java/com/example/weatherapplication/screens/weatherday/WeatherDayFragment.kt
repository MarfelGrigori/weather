package com.example.weatherapplication.screens.weatherday

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapplication.databinding.FragmentSecondBinding
import com.example.weatherapplication.di.BaseFragment
import com.example.weatherapplication.di.activityViewModelProvider
import com.example.weatherapplication.screens.weatherday.recyclerAdapter.WeatherDayAdapter
import com.example.weatherapplication.viewModel.MainViewModel

class WeatherDayFragment : BaseFragment() {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding
    lateinit var viewModel: MainViewModel

    lateinit var adapter: WeatherDayAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = activityViewModelProvider()
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.recyclerView
        adapter = WeatherDayAdapter()
        binding?.recyclerView?.adapter = adapter
        viewModel.weatherToDay.observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}