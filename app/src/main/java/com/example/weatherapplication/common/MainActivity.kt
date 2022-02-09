package com.example.weatherapplication.common


import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapplication.R
import com.example.weatherapplication.common.utils.Location
import com.example.weatherapplication.home.viewModel.HomeViewModel
import com.example.weatherapplication.weatherDay.viewModel.WeatherDayViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
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
        val location = Location(viewModel._location.first,viewModel._location.second)
        location.getLocation(this, viewModel, viewModel1)
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe ({ response ->
                location.lat = response.lat
                location.lon = response.lon
            },{error->Log.e("TAG",error.stackTraceToString())} )
        viewModel.loadAll()
        viewModel1.loadData()
    }
}



