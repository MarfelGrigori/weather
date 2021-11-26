package com.example.weatherapplication.screens.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.weatherapplication.viewModel.MainViewModel
import com.example.weatherapplication.databinding.FragmentSecondBinding
import com.example.weatherapplication.screens.second.recyclerAdapter.Weather5DaysAdapter

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
private val viewModel by activityViewModels<MainViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView
        val adapter = Weather5DaysAdapter()
        binding.recyclerView.adapter = adapter
        viewModel.weatherTo5Days.observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }
}