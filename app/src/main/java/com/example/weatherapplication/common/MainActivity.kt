package com.example.weatherapplication.common


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapplication.R
import com.example.weatherapplication.common.utils.LocationService
import com.example.weatherapplication.home.viewModel.HomeViewModel
import com.example.weatherapplication.weatherDay.viewModel.WeatherDayViewModel
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<HomeViewModel> { viewModelFactory }
    private val viewModel1 by viewModels<WeatherDayViewModel> { viewModelFactory }

    @Inject
    lateinit var locationService: LocationService
    private val compositeDisposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getPermission()
        viewModel.loadAll()
        viewModel1.loadData()
    }

    private fun getPermission() {
        val requestPermissionLauncher =
            this.registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                if (it) defineLocation()
            }
        definePermission(requestPermissionLauncher)
    }

    private fun defineLocation() {
        locationService.getLocation()
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                viewModel.setLocation(response.lat, response.lon)
                viewModel1.setLocation(response.lat, response.lon)
            }, { error -> Log.e("TAG", error.stackTraceToString()) })
            .also { compositeDisposable.add(it) }
    }

    private fun definePermission(requestPermissionLauncher: ActivityResultLauncher<String>) {
        if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            defineLocation()
        } else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}



