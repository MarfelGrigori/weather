package com.example.weatherapplication.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapplication.screens.first.entities.WeatherWeek
import com.example.weatherapplication.screens.second.entities.WeatherTo5Days
import com.example.weatherapplication.useCases.LoadWeather5DayUseCase
import com.example.weatherapplication.useCases.LoadWeatherTodayUseCase
import com.example.weatherapplication.useCases.LoadWeatherWeekUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val loadWeatherUseCase: LoadWeather5DayUseCase,
    private val loadWeatherTodayUseCase: LoadWeatherTodayUseCase,
    private val loadWeatherWeekUseCase: LoadWeatherWeekUseCase
) : ViewModel() {
    private val MIN_LATITUDE = -90.0
    private val MAX_LATITUDE = 90.0
    private val MIN_LONGITUDE = -180.0
    private val MAX_LONGITUDE = 180.0

    //    private val loadWeatherUseCase = LoadWeather5DayUseCase()
//    private val loadWeatherTodayUseCase = LoadWeatherTodayUseCase()
//    private val loadWeatherWeekUseCase = LoadWeatherWeekUseCase()
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
    private var _location: Pair<Double, Double> = Pair(1000.0, 1000.0)
    private val _weatherWeek = MutableLiveData<List<WeatherWeek>>()
    val weatherWeek: LiveData<List<WeatherWeek>> = _weatherWeek
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadAll() {
        _isLoading.value = true
        val lat = _location.first.toString()
        val lon = _location.second.toString()
        if (lat.toDouble() in MIN_LATITUDE..MAX_LATITUDE && lon.toDouble() in MIN_LONGITUDE..MAX_LONGITUDE) {
            loadWeatherToday(lat, lon)
            loadWeatherTo5Days(lat, lon)
            loadWeatherWeek(lat, lon)
            _isLoading.postValue(false)
        }
    }

    fun setLocation(latNew: Double, lonNew: Double) {
        val lat = _location.first
        val lon = _location.second
        if (lat !in latNew - 0.01..latNew + 0.01 || lon !in lonNew - 0.01..lonNew + 0.01)
            _location = (Pair(latNew, lonNew))
        loadAll()
    }

    private fun loadWeatherToday(lat: String, lon: String) {
        Log.e("TAG", "loadWeatherToday: $lat $lon")
        ioScope.launch {
            try {
                _temperatureToday.postValue(
                    loadWeatherTodayUseCase.loadWeatherToday(
                        lat,
                        lon,
                    )?.temp
                )
                _mainToday.postValue(
                    loadWeatherTodayUseCase.loadWeatherToday(
                        lat,
                        lon,

                        )?.main
                )
                _currentCity.postValue(
                    loadWeatherTodayUseCase.loadWeatherToday(
                        lat,
                        lon,

                        )?.city
                )
                _currentCountry.postValue(
                    loadWeatherTodayUseCase.loadWeatherToday(
                        lat,
                        lon,
                    )?.country
                )
            } catch (e: Exception) {
                _errorBus.postValue(e.message)
            }
        }

    }

    private fun loadWeatherTo5Days(lat: String, lon: String) {
        ioScope.launch {
            try {
                _weatherTo5Days.postValue(
                    loadWeatherUseCase.loadWeatherTo5Days(
                        lat,
                        lon,

                        )
                )
            } catch (e: Exception) {
                _errorBus.postValue(e.message)
            }
        }
    }

    private fun loadWeatherWeek(lat: String, lon: String) {
        ioScope.launch {
            try {
                _weatherWeek.postValue(loadWeatherWeekUseCase.loadWeatherWeek(lat, lon))
            } catch (e: Exception) {
                _errorBus.postValue(e.message)
            }
        }
    }

}
