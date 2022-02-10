package com.example.weatherapplication.common.utils

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Task
import io.reactivex.rxjava3.core.Single

data class Location(var lat: Double, var lon: Double)
class LocationService(context: Context) {
    private val fusedLocationProvider: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    fun getLocation(): Single<Location> = Single.create { emitter ->
        val cancellationTokenSource = CancellationTokenSource()
        val currentLocationTask: Task<android.location.Location> =
            fusedLocationProvider.getCurrentLocation(
                PRIORITY_HIGH_ACCURACY,
                cancellationTokenSource.token
            )
        currentLocationTask.addOnCompleteListener { task: Task<android.location.Location> ->
            emitter.onSuccess(Location(task.result.latitude, task.result.longitude))
        }
        currentLocationTask.addOnFailureListener { emitter.onError(it) }
        emitter.setCancellable {
            cancellationTokenSource.cancel()
        }
    }
}




