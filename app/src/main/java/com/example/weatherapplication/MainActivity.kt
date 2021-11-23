package com.example.weatherapplication

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.weatherapplication.recycler_adapter.Weather5DaysAdapter
import com.example.weatherapplication.recycler_adapter.WeatherWeekAdapter
import com.google.android.gms.location.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    companion object {
        internal const val KEY = "a5000964c71443402a055b2152004987"
    }

    private lateinit var fusedLocationProvider: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionBar = (this).supportActionBar
        if (viewModel.location.value != null)
            viewModel.loadAll(
                viewModel.location.value?.first.toString(),
                viewModel.location.value?.second.toString(),
                KEY
            )

        val city = findViewById<TextView>(R.id.city)
        val main = findViewById<TextView>(R.id.main)
        val temperature = findViewById<TextView>(R.id.temperature)
        val image = findViewById<ImageView>(R.id.image)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        viewModel.weatherToday.observe(this) {
            city.text = ("${it.city}, ${it.country}")
            main.text = it.main
            temperature.text = ("${it.temp}°C")
            when (it.main) {
                ("Clouds") -> {
                    image.setImageResource(R.drawable.cloud)
                }
                ("Rain") -> {
                    image.setImageResource(R.drawable.union)
                }
                ("Clear") -> {
                    image.setImageResource(R.drawable.sun)
                }
                ("Snow") -> {
                    image.setImageResource(R.drawable.snow)
                }
            }
        }

        viewModel.location.observe(this) {
            if (viewModel.weatherToday.value == null || viewModel.weatherTo5Days.value == null)
                viewModel.loadAll(it.first.toString(), it.second.toString(), KEY)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.weatherWeek.observe(this) {
            Log.e("TAG", it.toString())
            recyclerView.adapter = WeatherWeekAdapter(it)
        }

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
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            return true
        startActivity(Intent(this, SecondActivity::class.java))
        return super.onOptionsItemSelected(item)
    }
}



