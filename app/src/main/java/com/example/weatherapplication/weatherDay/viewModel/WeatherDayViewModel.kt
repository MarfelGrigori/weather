package com.example.weatherapplication.weatherDay.viewModel

import androidx.lifecycle.ViewModel
import com.example.weatherapplication.R
import com.example.weatherapplication.weatherDay.models.WeatherDayWithAllParameters
import com.example.weatherapplication.weatherDay.mapper.toWeatherDay
import com.example.weatherapplication.common.utils.Converter
import com.example.weatherapplication.weatherDay.useCases.loadWeather.LoadWeatherDay
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

private const val MIN_LATITUDE = -90.0
private const val MAX_LATITUDE = 90.0
private const val MIN_LONGITUDE = -180.0
private const val MAX_LONGITUDE = 180.0

open class WeatherDayViewModel @Inject constructor(private val loadWeatherUseCase: LoadWeatherDay) :
    ViewModel() {

    var _location: Pair<Double, Double> = Pair(1000.0, 1000.0)

    private val compositeDisposable = CompositeDisposable()

    private val _weatherDay = MutableStateFlow<List<WeatherDayWithAllParameters>?>(emptyList())
    val weatherToDay: StateFlow<List<WeatherDayWithAllParameters>?> = _weatherDay

    private val _errorBus = Converter.MutableSingleEventFlow<String>()
    val errorBus: SharedFlow<String?> = _errorBus

    var list = emptyList<WeatherDayWithAllParameters>()
    fun loadData() {
        val (lat, lon) = _location
        if (lat in MIN_LATITUDE..MAX_LATITUDE && lon in MIN_LONGITUDE..MAX_LONGITUDE) {
            loadWeatherDay(lat.toString(), lon.toString())
            checkError()
        }
    }

    fun setLocation(latNew: Double, lonNew: Double) {
        val (lat,lon) = _location
        if (lat !in latNew - 0.01..latNew + 0.01 || lon !in lonNew - 0.01..lonNew + 0.01)
            _location = (Pair(latNew, lonNew))
        loadData()
    }

    private fun loadWeatherDay(lat: String, lon: String) {
        loadWeatherUseCase.invoke(lat, lon)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { weatherDayResponse ->
                _weatherDay.value = weatherDayResponse.toWeatherDay()
                list = weatherDayResponse.toWeatherDay()
            }
            .also { compositeDisposable.add(it) }
    }

    private fun checkError() {
        if (_errorBus.toString() == R.string.error_network_text.toString()) _errorBus.tryEmit(R.string.error.toString())
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}