package com.example.weatherapplication.common


import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapplication.R
import com.example.weatherapplication.home.viewModel.HomeViewModel
import com.example.weatherapplication.weatherDay.viewModel.WeatherDayViewModel
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnCompleteListener
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleEmitter
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
            },{error->Log.e("TAG",error.stackTraceToString())})

        viewModel.loadAll()
        viewModel1.loadData()

//        Single<String> create < Event ? > { emitter ->
//            val listener: Callback = object : Callback() {
//                fun onEvent(e: String?) {
//                    emitter.onSuccess(e)
//                }
//
//                fun onFailure(e: Exception?) {
//                    emitter.onError(e)
//                }
//            }
//            val c: AutoCloseable = api.someMethod(listener)
//            emitter.setCancellable { c.close() }
//        }
    }
   inner class Location (var lat : Double, var lon: Double) {
        fun getLocation(
            context: AppCompatActivity,
            viewModel: HomeViewModel,
            viewModel1: WeatherDayViewModel
        ): Single<Location> = Single.create  { emitter->
            val setLocation: (Double, Double) -> Unit = { lat: Double, lon: Double ->
                viewModel.setLocation(lat, lon)
                viewModel1.setLocation(lat, lon)
            }
            val fusedLocationProvider: FusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(this@MainActivity)
            val locationCallBack = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult?) {
                    locationResult ?: return
                    for (location in locationResult.locations) {
                        if (!emitter.isDisposed)
                            setLocation(location.latitude, location.longitude)
                        emitter.onSuccess(Location(location.latitude, location.longitude))
                    }
                }
            }

            val requestPermissionLauncher =
                this@MainActivity.registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                    if (it)
                        startLocationUpdates(locationCallBack, fusedLocationProvider)
                }

            when (PackageManager.PERMISSION_GRANTED) {
                ContextCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) -> {
                    startLocationUpdates(locationCallBack, fusedLocationProvider)
                }
                else -> {
                    requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                }
            }
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
}



