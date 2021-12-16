package com.example.weatherapplication.screens.home.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapplication.R
import com.example.weatherapplication.screens.home.entities.WeatherWeekWithAllParameters
import com.example.weatherapplication.screens.weatherday.entities.WeatherDay
import com.example.weatherapplication.useCases.LoadWeatherTodayUseCase
import com.example.weatherapplication.useCases.LoadWeatherWeekUseCase
import com.example.weatherapplication.utils.Picture
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

open class MainViewModel @Inject constructor(
    private val loadWeatherTodayUseCase: LoadWeatherTodayUseCase,
    private val loadWeatherWeekUseCase: LoadWeatherWeekUseCase
) : ViewModel() {
    private val MIN_LATITUDE = -90.0
    private val MAX_LATITUDE = 90.0
    private val MIN_LONGITUDE = -180.0
    private val MAX_LONGITUDE = 180.0
    private var _location: Pair<Double, Double> = Pair(1000.0, 1000.0)

    private val ioScope = CoroutineScope(Dispatchers.IO)

    private val _temperatureToday = MutableStateFlow<String?>(null)
    val temperatureToday: StateFlow<String?> = _temperatureToday

    private val _mainToday = MutableStateFlow<String?>(null)
    val mainToday: StateFlow<String?> = _mainToday

    private val _currentCity = MutableStateFlow<String?>(null)
    val currentCity: StateFlow<String?> = _currentCity

    private val _currentCountry = MutableStateFlow<String?>(null)
    val currentCountry: StateFlow<String?> = _currentCountry

    private val _weatherDay = MutableStateFlow<List<WeatherDay>?>(null)
    val weatherToDay: StateFlow<List<WeatherDay>?> = _weatherDay

    private val _errorBus = MutableStateFlow<String?>(null)
    val errorBus: StateFlow<String?> = _errorBus

    private val _weatherWeek = MutableStateFlow<List<WeatherWeekWithAllParameters>?>(emptyList())
    val weatherWeek: MutableStateFlow<List<WeatherWeekWithAllParameters>?> = _weatherWeek

    private val _isLoading = MutableStateFlow<Boolean?>(null)
    val isLoading: StateFlow<Boolean?> = _isLoading

    private val _picture = MutableStateFlow<Picture?>(null)
    val picture: StateFlow<Picture?> = _picture

    fun loadAll() {
        _isLoading.value = true
        val lat = _location.first.toString()
        val lon = _location.second.toString()
        if (lat.toDouble() in MIN_LATITUDE..MAX_LATITUDE && lon.toDouble() in MIN_LONGITUDE..MAX_LONGITUDE) {
            loadWeatherToday(lat, lon)
            loadWeatherWeek(lat, lon)
            checkError()
            _isLoading.value = false
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
                val weatherToday = loadWeatherTodayUseCase.loadWeatherToday(lat, lon)
                _temperatureToday.value= weatherToday?.temp.toString()
                _mainToday.value =weatherToday?.main
                _currentCity.value=weatherToday?.city
                _currentCountry.value=weatherToday?.country
            } catch (e: Exception) {
                _errorBus.value=e.message
            }
        }
    }

    private fun loadWeatherWeek(lat: String, lon: String) {
        ioScope.launch {
            try {
                _weatherWeek.value =
                    loadWeatherWeekUseCase.loadWeatherWeek(lat, lon)?.subList(0, 6)

            } catch (e: Exception) {
                _errorBus.value =e.message
            }
        }
    }

    private fun checkError() {
        if (_errorBus.value == R.string.error_network_text.toString()) _errorBus.value =
            R.string.error.toString()
    }
}
