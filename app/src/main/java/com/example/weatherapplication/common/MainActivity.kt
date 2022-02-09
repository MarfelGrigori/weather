package com.example.weatherapplication.common


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapplication.R
import com.example.weatherapplication.common.utils.Location
import com.example.weatherapplication.common.utils.LocationService
import com.example.weatherapplication.home.viewModel.HomeViewModel
import com.example.weatherapplication.weatherDay.viewModel.WeatherDayViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject


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
        val location = Location(viewModel._location.first, viewModel._location.second)

        LocationService.getLocation(this)
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                viewModel.setLocation(response.lat, response.lon)
                viewModel1.setLocation(response.lat, response.lon)
            }, { error -> Log.e("TAG", error.stackTraceToString()) })
        getPermission(location)
        viewModel.loadAll()
        viewModel1.loadData()
    }

    private fun getPermission(location: Location) {
        val requestPermissionLauncher =
            this.registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                if (it)
                    LocationService.getLocation(this)
            }

        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) -> {
                LocationService.getLocation(this)
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }
}



