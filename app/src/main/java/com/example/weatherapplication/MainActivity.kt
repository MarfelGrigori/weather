package com.example.weatherapplication

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    companion object {
        private const val KEY = "a5000964c71443402a055b2152004987"
    }
    private lateinit var fusedLocationProvider: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.loadAll(
            viewModel.location.value?.first.toString(),
            viewModel.location.value?.second.toString(),
            KEY
        )


        viewModel.errorBus.observe(this) {
            MaterialAlertDialogBuilder(this).setTitle("Error").setMessage(it).show()
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



