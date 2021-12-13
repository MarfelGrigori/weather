package com.example.weatherapplication


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapplication.screens.home.HomeFragment
import com.example.weatherapplication.screens.weatherday.WeatherDayFragment
import com.example.weatherapplication.utils.Location
import com.example.weatherapplication.viewModel.MainViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<MainViewModel> { viewModelFactory }
    private val firstFragment = HomeFragment()
    private lateinit var fusedLocationProvider: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view_tag, firstFragment).commit()
        fusedLocationProvider = LocationServices.getFusedLocationProviderClient(this)
        val location = Location()
        location.getLocation(this, viewModel)
        viewModel.loadAll()
    }
}



