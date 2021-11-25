package com.example.weatherapplication


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapplication.screens.first.FirstFragment
import com.example.weatherapplication.screens.second.SecondFragment
import com.example.weatherapplication.utils.Location
import com.example.weatherapplication.viewModel.MainViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

const val KEY = "a5000964c71443402a055b2152004987"

class MainActivity : AppCompatActivity() {

    private val firstFragment = FirstFragment()
    private val secondFragment = SecondFragment()
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var fusedLocationProvider: FusedLocationProviderClient
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel by viewModels<MainViewModel>()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view_tag, firstFragment).commit()
        fusedLocationProvider = LocationServices.getFusedLocationProviderClient(this)
        val currentLocation = Location(this)
        currentLocation.getLocation(viewModel)
        loadData(viewModel)
    }

    private fun loadData(viewModel:MainViewModel) {
        viewModel.loadData()
        viewModel.location.observe(this) {
            viewModel.loadAll(KEY)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            return true
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view_tag, secondFragment).addToBackStack(null).commit()
        return super.onOptionsItemSelected(item)
    }
}



