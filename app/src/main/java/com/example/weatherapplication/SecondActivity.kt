package com.example.weatherapplication

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.recycler_adapter.Weather5DaysAdapter
import com.google.android.gms.location.*

class SecondActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private lateinit var fusedLocationProvider: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        if (viewModel.location.value != null)
            viewModel.loadAll(
                viewModel.location.value?.first.toString(),
                viewModel.location.value?.second.toString(),
                MainActivity.KEY
            )
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.weatherTo5Days.observe(this){
            Log.e("TAG",it.toString())
//            text.text = it.toString()
            recyclerView.adapter = Weather5DaysAdapter(it)
        }
        viewModel.location.observe(this) {
            if(viewModel.weatherToday.value==null||viewModel.weatherTo5Days.value==null)
                viewModel.loadAll(it.first.toString(), it.second.toString(), MainActivity.KEY)
        }

        fusedLocationProvider = LocationServices.getFusedLocationProviderClient(this)

        val locationCallBack = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                for (location in locationResult.locations) {
                    viewModel.setLocation(location.latitude, location.longitude)
                }
            }
        }

        val requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                if (it)
                    startLocationUpdates(locationCallBack)
            }

        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) -> {
                startLocationUpdates(locationCallBack)
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }
    @SuppressLint("MissingPermission")
    private fun startLocationUpdates(locationCallBack: LocationCallback) {
        fusedLocationProvider.requestLocationUpdates(
            getRequest(), locationCallBack,
            Looper.getMainLooper()
        )
    }
    private fun getRequest() = LocationRequest.create().apply {
        interval = 1000
        fastestInterval = 2000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    }
