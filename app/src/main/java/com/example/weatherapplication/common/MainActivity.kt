package com.example.weatherapplication.common


import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapplication.R
import com.example.weatherapplication.home.viewModel.HomeViewModel
import com.example.weatherapplication.weatherDay.viewModel.WeatherDayViewModel
import com.example.weatherapplication.common.utils.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.thread


class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<HomeViewModel> { viewModelFactory }
    private val viewModel1 by viewModels<WeatherDayViewModel> { viewModelFactory }
    private lateinit var fusedLocationProvider: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fusedLocationProvider = LocationServices.getFusedLocationProviderClient(this)
        val location = Location()
        location.getLocation(this, viewModel, viewModel1)
        viewModel.loadAll()
        viewModel1.loadData()
    }
}



