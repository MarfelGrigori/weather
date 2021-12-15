package com.example.weatherapplication.screens.weatherday.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapplication.R
import com.example.weatherapplication.screens.weatherday.entities.WeatherDayWithAllParameters
import com.example.weatherapplication.useCases.LoadWeatherDayUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

open class SecondViewModel @Inject constructor(private val loadWeatherUseCase: LoadWeatherDayUseCase) :
    ViewModel() {
    private val MIN_LATITUDE = -90.0
    private val MAX_LATITUDE = 90.0
    private val MIN_LONGITUDE = -180.0
    private val MAX_LONGITUDE = 180.0
    private var _location: Pair<Double, Double> = Pair(1000.0, 1000.0)

    private val ioScope = CoroutineScope(Dispatchers.IO)

    private val _weatherDay = MutableLiveData<List<WeatherDayWithAllParameters>>()
    val weatherToDay: LiveData<List<WeatherDayWithAllParameters>> = _weatherDay

    private val _errorBus = MutableLiveData<String>()
    val errorBus: LiveData<String> = _errorBus

    var date: String? = null

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
        ioScope.launch {
            try {
                _weatherDay.postValue(
                    loadWeatherUseCase.loadWeatherDay(lat, lon)
                        ?.filter { it.time.contains(date.toString()) })
            } catch (e: Exception) {
                _errorBus.postValue(e.message)
            }
        }
    }

    private fun checkError() {
        if (_errorBus.value == R.string.error_network_text.toString()) _errorBus.value =
            R.string.error.toString()
    }
}