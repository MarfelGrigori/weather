package com.example.weatherapplication.screens.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapplication.databinding.FragmentSecondBinding
import com.example.weatherapplication.di.BaseFragment
import com.example.weatherapplication.di.activityViewModelProvider
import com.example.weatherapplication.screens.second.recyclerAdapter.Weather5DaysAdapter
import com.example.weatherapplication.viewModel.MainViewModel
import javax.inject.Inject

class SecondFragment : BaseFragment() {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var adapter: Weather5DaysAdapter
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
        adapter = Weather5DaysAdapter()
        binding?.recyclerView?.adapter = adapter
        viewModel.weatherTo5Days.observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}