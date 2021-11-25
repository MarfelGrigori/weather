package com.example.weatherapplication.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapplication.KEY
import com.example.weatherapplication.screens.second.entities.WeatherTo5Days
import com.example.weatherapplication.screens.first.entities.WeatherWeek
import com.example.weatherapplication.useCases.LoadWeather5DayUseCase
import com.example.weatherapplication.useCases.LoadWeatherTodayUseCase
import com.example.weatherapplication.useCases.LoadWeatherWeekUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val loadWeatherUseCase = LoadWeather5DayUseCase()
    private val loadWeatherTodayUseCase = LoadWeatherTodayUseCase()
    private val loadWeatherWeekUseCase = LoadWeatherWeekUseCase()
    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val _temperatureToday = MutableLiveData<Int>()
    val temperatureToday: LiveData<Int> = _temperatureToday
    private val _mainToday = MutableLiveData<String>()
    val mainToday: LiveData<String> = _mainToday
    private val _currentCity = MutableLiveData<String>()
    val currentCity: LiveData<String> = _currentCity
    private val _currentCountry = MutableLiveData<String>()
    val currentCountry: LiveData<String> = _currentCountry
    private val _weatherTo5Days = MutableLiveData<List<WeatherTo5Days>>()
    val weatherTo5Days: LiveData<List<WeatherTo5Days>> = _weatherTo5Days
    private val _errorBus = MutableLiveData<String>()
    val errorBus: LiveData<String> = _errorBus
    private val _location = MutableLiveData<Pair<Double, Double>>()
    val location: LiveData<Pair<Double, Double>> = _location
    private val _weatherWeek = MutableLiveData<List<WeatherWeek>>()
    val weatherWeek: LiveData<List<WeatherWeek>> = _weatherWeek
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadAll(appid: String) {
        _isLoading.value = true
        val lat = location.value?.first.toString()
        val lon = location.value?.second.toString()
        loadWeatherToday(lat, lon, appid)
        loadWeatherTo5Days(lat, lon, appid)
        loadWeatherWeek(lat, lon, appid)
        Log.e("TAG", "loadAll")
        _isLoading.postValue(false)
    }

    fun setLocation(latNew: Double, lonNew: Double) {
        val lat = _location.value?.first ?: 53.5360
        val lon = _location.value?.second ?: 27.3400
        if (lat !in latNew - 0.01..latNew + 0.01 || lon !in lonNew - 0.01..lonNew + 0.01)
            _location.postValue(Pair(latNew, lonNew))
    }

    private fun loadWeatherToday(lat: String, lon: String, appid: String) {
        Log.e("TAG", "loadWeatherToday: $lat $lon")
        ioScope.launch {
            try {
                _temperatureToday.postValue(
                    loadWeatherTodayUseCase.loadWeatherToday(
                        lat,
                        lon,
                        appid
                    )?.temp
                )
                _mainToday.postValue(
                    loadWeatherTodayUseCase.loadWeatherToday(
                        lat,
                        lon,
                        appid
                    )?.main
                )
                _currentCity.postValue(
                    loadWeatherTodayUseCase.loadWeatherToday(
                        lat,
                        lon,
                        appid
                    )?.city
                )
                _currentCountry.postValue(
                    loadWeatherTodayUseCase.loadWeatherToday(
                        lat,
                        lon,
                        appid
                    )?.country
                )
            } catch (e: Exception) {
                _errorBus.postValue(e.message)
            }
        }

    }

    private fun loadWeatherTo5Days(lat: String, lon: String, appid: String) {
        ioScope.launch {
            try {
                _weatherTo5Days.postValue(
                    loadWeatherUseCase.loadWeatherTo5Days(
                        lat,
                        lon,
                        appid
                    )
                )
            } catch (e: Exception) {
                _errorBus.postValue(e.message)
            }
        }
    }

    private fun loadWeatherWeek(lat: String, lon: String, appid: String) {
        ioScope.launch {
            try {
                _weatherWeek.postValue(loadWeatherWeekUseCase.loadWeatherWeek(lat, lon, appid))
            } catch (e: Exception) {
                _errorBus.postValue(e.message)
            }
        }
    }
    fun loadData (){
        if (location.value != null)
            loadAll(KEY)
    }
}
