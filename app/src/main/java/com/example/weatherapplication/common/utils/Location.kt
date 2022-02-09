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
import com.google.android.gms.tasks.OnCompleteListener
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleEmitter

