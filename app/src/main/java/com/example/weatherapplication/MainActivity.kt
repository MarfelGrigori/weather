package com.example.weatherapplication


import android.os.Bundle
import android.util.Log
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


class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private val firstFragment = FirstFragment()
    private val secondFragment = SecondFragment()
    private lateinit var fusedLocationProvider: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view_tag, firstFragment).commit()
        fusedLocationProvider = LocationServices.getFusedLocationProviderClient(this)
        val location = Location()
        location.getLocation(this, viewModel)
        loadData()
    }

    private fun loadData() {
        viewModel.location.observe(this) {
            viewModel.loadAll()
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
            .replace(R.id.fragment_container_view_tag, secondFragment).commit()
        return super.onOptionsItemSelected(item)
    }
}



