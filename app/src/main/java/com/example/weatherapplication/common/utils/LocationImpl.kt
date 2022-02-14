package com.example.weatherapplication.common.utils

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

data class Location(var lat: Double, var lon: Double)

class LocationServiceImpl @Inject constructor(context: Context) : LocationService {

    private val fusedLocationProvider = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    override fun getLocation(): Single<Location> = Single.create { emitter ->
        val cancellation = CancellationTokenSource()
        fusedLocationProvider.getCurrentLocation(PRIORITY_HIGH_ACCURACY, cancellation.token)
            .addOnCompleteListener {
                emitter.onSuccess(it.result.toLocation())
            }
            .addOnFailureListener { emitter.onError(it) }
        emitter.setCancellable { cancellation.cancel() }
    }

    private fun android.location.Location.toLocation(): Location = Location(latitude, longitude)
}




