package com.example.weatherapplication.weatherDay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.weatherapplication.common.di.BaseFragment
import com.example.weatherapplication.common.utils.Converter.subscribe
import com.example.weatherapplication.databinding.FragmentSecondBinding
import com.example.weatherapplication.weatherDay.models.WeatherDay
import com.example.weatherapplication.weatherDay.viewModel.WeatherDayViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil

class SecondFragment : BaseFragment() {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    val viewModel: WeatherDayViewModel by activityViewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemAdapter = ItemAdapter<WeatherDay>()
        val fastAdapter = FastAdapter.with(itemAdapter)
        binding.recyclerView.adapter = fastAdapter
        binding.recyclerView.itemAnimator = null
        viewModel.loadData()
        val items = ArrayList<WeatherDay>()
        viewModel.list.forEach {
            if (arguments?.getString("message")
                    .let { it1 -> it1?.let { it2 -> it.time.contains(it2) } } == true
            )
                items.add(WeatherDay(it))
        }
        viewModel.weatherToDay.subscribe(lifecycleScope) {
            FastAdapterDiffUtil[itemAdapter] = items
        }
        viewModel.errorBus.subscribe(lifecycleScope) {
            MaterialAlertDialogBuilder(requireContext()).setTitle(it)
                .setMessage(it).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}