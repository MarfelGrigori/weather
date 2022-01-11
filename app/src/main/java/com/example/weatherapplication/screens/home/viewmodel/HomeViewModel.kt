package com.example.weatherapplication.screens.home.viewmodel


import androidx.lifecycle.ViewModel
import com.example.weatherapplication.R
import com.example.weatherapplication.screens.home.entities.WeatherWeekWithAllParameters
import com.example.weatherapplication.screens.home.networking.toWeatherToday
import com.example.weatherapplication.screens.home.networking.toWeatherWeek
import com.example.weatherapplication.screens.weatherday.entities.WeatherDay
import com.example.weatherapplication.useCases.LoadWeatherTodayUseCase
import com.example.weatherapplication.useCases.LoadWeatherWeekUseCase
import com.example.weatherapplication.utils.Picture
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

open class HomeViewModel @Inject constructor(
    private val loadWeatherTodayUseCase: LoadWeatherTodayUseCase,
    private val loadWeatherWeekUseCase: LoadWeatherWeekUseCase
) : ViewModel() {
    private val MIN_LATITUDE = -90.0
    private val MAX_LATITUDE = 90.0
    private val MIN_LONGITUDE = -180.0
    private val MAX_LONGITUDE = 180.0
     var _location: Pair<Double, Double> = Pair(1000.0, 1000.0)

    private val _temperatureToday = MutableStateFlow<String?>("")
    val temperatureToday: StateFlow<String?> = _temperatureToday

    private val _mainToday = MutableStateFlow<String?>("")
    val mainToday: StateFlow<String?> = _mainToday

    private val _currentCity = MutableStateFlow<String?>("")
    val currentCity: StateFlow<String?> = _currentCity

    private val _currentCountry = MutableStateFlow<String?>("")
    val currentCountry: StateFlow<String?> = _currentCountry

    private val _weatherDay = MutableStateFlow<List<WeatherDay>?>(emptyList())
    val weatherToDay: StateFlow<List<WeatherDay>?> = _weatherDay

    private val _errorBus = MutableSharedFlow<String?>(
        replay = 1,
        extraBufferCapacity = 0,
        onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val errorBus: SharedFlow<String?> = _errorBus

    private val _weatherWeek = MutableStateFlow<List<WeatherWeekWithAllParameters>?>(emptyList())
    val weatherWeek: MutableStateFlow<List<WeatherWeekWithAllParameters>?> = _weatherWeek

    private val _isLoading = MutableStateFlow<Boolean?>(true)
    val isLoading: StateFlow<Boolean?> = _isLoading

    private val _picture = MutableStateFlow<Picture?>(Picture.CLOUDS)
    val picture: StateFlow<Picture?> = _picture

    private val compositeDisposable = CompositeDisposable()

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
        compositeDisposable.add(loadWeatherTodayUseCase.loadWeatherToday(lat, lon).subscribeOn(
            Schedulers.io()
        ).observeOn(AndroidSchedulers.mainThread()).subscribe { response ->
            val weatherToday = response.toWeatherToday()
            _temperatureToday.value = weatherToday.temp
            _mainToday.value = weatherToday.main
            _currentCity.value = weatherToday.city
            _currentCountry.value = weatherToday.country
        })

    }

    private fun loadWeatherWeek(lat: String, lon: String) {
        compositeDisposable.add(loadWeatherWeekUseCase.loadWeatherWeek(lat, lon).subscribeOn(
            Schedulers.io()
        ).observeOn(AndroidSchedulers.mainThread()).subscribe { list ->
            _weatherWeek.value = list.toWeatherWeek()
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
