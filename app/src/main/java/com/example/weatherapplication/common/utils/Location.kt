package com.example.weatherapplication.common.utils

import android.annotation.SuppressLint
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnCompleteListener
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleEmitter

data class Location(var lat: Double, var lon: Double)
class LocationService{
    fun getLocation(
        context: AppCompatActivity
    ): Single<Location> = Single.create { emitter ->
        val fusedLocationProvider: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(context)
        val locationCallBack = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                for (location in locationResult.locations) {
                    if (!emitter.isDisposed)
                        emitter.onSuccess(Location(location.latitude, location.longitude))
                }
            }
        }
        startLocationUpdates(locationCallBack, fusedLocationProvider)
        emitter.setCancellable { getOnCompleteListener(emitter) }
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

    private fun getOnCompleteListener(emitter: SingleEmitter<Location>): OnCompleteListener<Void> {
        return OnCompleteListener { task ->
            if (!task.isSuccessful) {
                emitter.tryOnError(
                    task.exception
                        ?: IllegalStateException("Can't get location from FusedLocationProviderClient")
                )
            }
        }
    }

    private fun getRequest() = LocationRequest.create().apply {
        interval = 10000
        fastestInterval = 20000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }
}

