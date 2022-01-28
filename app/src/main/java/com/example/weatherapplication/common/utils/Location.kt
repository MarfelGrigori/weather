package com.example.weatherapplication.common.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Looper
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.weatherapplication.home.viewModel.HomeViewModel
import com.example.weatherapplication.weatherDay.viewModel.WeatherDayViewModel
import com.google.android.gms.location.*
import io.reactivex.rxjava3.core.Single

data class Location (var lat : Double, var lon: Double) {
    fun getLocation(
        context: AppCompatActivity,
        viewModel: HomeViewModel,
        viewModel1: WeatherDayViewModel
    ): Single<Location> {
        val setLocation: (Double, Double) -> Unit = { lat: Double, lon: Double ->
            viewModel.setLocation(lat, lon)
            viewModel1.setLocation(lat, lon)
        }

        val fusedLocationProvider: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(context)
        val locationCallBack = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                for (location in locationResult.locations) {
                    setLocation(location.latitude, location.longitude)
                }
            }
        }

        val requestPermissionLauncher =
            context.registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                if (it)
                    startLocationUpdates(locationCallBack, fusedLocationProvider)
            }

        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) -> {
                startLocationUpdates(locationCallBack, fusedLocationProvider)
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
        return Single.just(Location(lat, lon))
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates(
        locationCallBack: LocationCallback,
        fusedLocationProvider: FusedLocationProviderClient
    ) {
        fusedLocationProvider.requestLocationUpdates(
            getRequest(), locationCallBack,
            Looper.getMainLooper()
        )
    }

    private fun getRequest() = LocationRequest.create().apply {
        interval = 10000
        fastestInterval = 20000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

}