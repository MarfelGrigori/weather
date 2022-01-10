package com.example.weatherapplication.screens.weatherday.viewmodel

import androidx.lifecycle.ViewModel
import com.example.weatherapplication.R
import com.example.weatherapplication.screens.weatherday.entities.WeatherDayWithAllParameters
import com.example.weatherapplication.screens.weatherday.networking.mapper.toWeatherDay
import com.example.weatherapplication.useCases.LoadWeatherDayUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

open class WeatherDayViewModel @Inject constructor(private val loadWeatherUseCase: LoadWeatherDayUseCase) :
    ViewModel() {
    private val MIN_LATITUDE = -90.0
    private val MAX_LATITUDE = 90.0
    private val MIN_LONGITUDE = -180.0
    private val MAX_LONGITUDE = 180.0
    private var _location: Pair<Double, Double> = Pair(1000.0, 1000.0)

    private val compositeDisposable = CompositeDisposable()

    private val _weatherDay = MutableStateFlow<List<WeatherDayWithAllParameters>?>(emptyList())
    val weatherToDay: StateFlow<List<WeatherDayWithAllParameters>?> = _weatherDay

    private val _errorBus = MutableSharedFlow<String?>(
        replay = 1,
        extraBufferCapacity = 0,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val errorBus: SharedFlow<String?> = _errorBus

    var date: String? = null
    var list = emptyList<WeatherDayWithAllParameters>()
    fun loadData() {
        val lat = _location.first.toString()
        val lon = _location.second.toString()
        if (lat.toDouble() in MIN_LATITUDE..MAX_LATITUDE && lon.toDouble() in MIN_LONGITUDE..MAX_LONGITUDE) {
            loadWeatherDay(lat, lon)
            checkError()
        }
    }

    fun setLocation(latNew: Double, lonNew: Double) {
        val lat = _location.first
        val lon = _location.second
        if (lat !in latNew - 0.01..latNew + 0.01 || lon !in lonNew - 0.01..lonNew + 0.01)
            _location = (Pair(latNew, lonNew))
        loadData()
    }

    private fun loadWeatherDay(lat: String, lon: String) {
        compositeDisposable.add(loadWeatherUseCase.loadWeatherDay(lat, lon).subscribeOn(
            Schedulers.io()
        ).observeOn(AndroidSchedulers.mainThread()).subscribe { weatherDayResponse ->
            _weatherDay.value = weatherDayResponse.toWeatherDay()
            list = weatherDayResponse.toWeatherDay()
        })
    }

    private fun checkError() {
        if (_errorBus.toString() == R.string.error_network_text.toString()) _errorBus.tryEmit(R.string.error.toString())
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}