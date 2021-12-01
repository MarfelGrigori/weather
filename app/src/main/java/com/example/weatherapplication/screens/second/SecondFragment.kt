package com.example.weatherapplication.screens.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.weatherapplication.databinding.FragmentSecondBinding
import com.example.weatherapplication.di.AppComponent
import com.example.weatherapplication.di.DaggerAppComponent
import com.example.weatherapplication.screens.second.recyclerAdapter.Weather5DaysAdapter
import com.example.weatherapplication.viewModel.MainViewModel
import javax.inject.Inject

class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding
    private lateinit var appComponent: AppComponent

    @Inject
    lateinit var adapter: Weather5DaysAdapter
    private val viewModel by activityViewModels<MainViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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