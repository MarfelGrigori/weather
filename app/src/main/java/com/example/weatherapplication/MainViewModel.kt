package com.example.weatherapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapplication.entities.WeatherTo5Days
import com.example.weatherapplication.entities.WeatherToday
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val loadWeatherUseCase = LoadWeatherUseCase()
    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val _weatherToday = MutableLiveData<WeatherToday>()
    val weatherToday: LiveData<WeatherToday> = _weatherToday
    private val _weatherTo5Days = MutableLiveData<List<WeatherTo5Days>>()
    val weatherTo5Days: LiveData<List<WeatherTo5Days>> = _weatherTo5Days
    private val _errorBus = MutableLiveData<String>()
    val errorBus: LiveData<String> = _errorBus
    private val _location = MutableLiveData<Pair<Double, Double>>()
    val location: LiveData<Pair<Double, Double>> = _location

    fun loadAll(lat: String, lon: String, appid: String) {
        loadWeatherToday(lat, lon, appid)
        loadWeatherTo5Days(lat, lon, appid)
        Log.e("TAG", "loadAll", )
    }

    fun setLocation(latNew: Double, lonNew: Double) {
        val lat = _location.value?.first ?: 53.5360
        val lon = _location.value?.second ?: 27.3400
        if (lat !in latNew - 0.01..latNew + 0.01|| lon !in lonNew-0.01..lonNew+0.01)
            _location.postValue(Pair(latNew, lonNew))
    }

    private fun loadWeatherToday(lat: String, lon: String, appid: String) {
        Log.e("TAG", "loadWeatherToday: $lat $lon", )
        ioScope.launch {
            try {
                _weatherToday.postValue(loadWeatherUseCase.loadWeatherToday(lat, lon, appid))
            } catch (e: Exception) {
                _errorBus.postValue(e.message)
            }
        }
    }

    private fun loadWeatherTo5Days(lat: String, lon: String, appid: String) {
        ioScope.launch {
            try {
                _weatherTo5Days.postValue(loadWeatherUseCase.loadWeatherTo5Days(lat, lon, appid))
            } catch (e: Exception) {
                _errorBus.postValue(e.message)
            }
        }
    }
}