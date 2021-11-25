package com.example.weatherapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapplication.databinding.FragmentSecondBinding
import com.example.weatherapplication.recycler_adapter.Weather5DaysAdapter

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater,container,false)
        val recyclerView = binding.recyclerView
        val adapter = Weather5DaysAdapter()
        recyclerView.adapter = adapter
        val viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewModel.weatherTo5Days.observe(viewLifecycleOwner) {
           adapter.initialize(it)
        }
        return binding.root

    }
}