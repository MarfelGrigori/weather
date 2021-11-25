package com.example.weatherapplication.screens.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapplication.viewModel.MainViewModel
import com.example.weatherapplication.databinding.FragmentSecondBinding
import com.example.weatherapplication.screens.second.recyclerAdapter.Weather5DaysAdapter

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: Weather5DaysAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        val recyclerView = binding.recyclerView
        adapter = Weather5DaysAdapter()
        recyclerView.adapter = adapter
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.weatherTo5Days.observe(viewLifecycleOwner) {
            adapter.initialize(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }
}