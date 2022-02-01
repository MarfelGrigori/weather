package com.example.weatherapplication.home.viewModel


import androidx.lifecycle.ViewModel
import com.example.weatherapplication.R
import com.example.weatherapplication.home.models.WeatherWeekWithAllParameters
import com.example.weatherapplication.home.useCase.loadWeather.networking.WeatherTodayResponse
import com.example.weatherapplication.home.mappers.toWeatherToday
import com.example.weatherapplication.home.mappers.toWeatherWeek
import com.example.weatherapplication.weatherDay.models.WeatherDay
import com.example.weatherapplication.common.utils.Converter.MutableSingleEventFlow
import com.example.weatherapplication.common.utils.Picture
import com.example.weatherapplication.home.useCase.loadWeather.LoadWeatherUseCase
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

open class HomeViewModel @Inject constructor(private val loadWeatherTodayUseCase: LoadWeatherUseCase) : ViewModel() {

    var _location: Pair<Double, Double> = Pair(1000.0, 1000.0)

    private val _temperatureToday = MutableStateFlow("")
    val temperatureToday: StateFlow<String> = _temperatureToday

    private val _mainToday = MutableStateFlow("")
    val mainToday: StateFlow<String> = _mainToday

    private val _currentCity = MutableStateFlow("")
    val currentCity: StateFlow<String?> = _currentCity

    private val _currentCountry = MutableStateFlow("")
    val currentCountry: StateFlow<String> = _currentCountry

    private val _weatherDay = MutableStateFlow<List<WeatherDay>>(emptyList())
    val weatherToDay: StateFlow<List<WeatherDay>> = _weatherDay


    private val _errorBus = MutableSingleEventFlow<String>()
    val errorBus: SharedFlow<String> = _errorBus

    private val _weatherWeek = MutableStateFlow<List<WeatherWeekWithAllParameters>>(emptyList())
    val weatherWeek: MutableStateFlow<List<WeatherWeekWithAllParameters>> = _weatherWeek

    private val _weatherToday = MutableStateFlow<List<WeatherWeekWithAllParameters>>(emptyList())
    val weatherToday: MutableStateFlow<List<WeatherWeekWithAllParameters>> = _weatherToday

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean?> = _isLoading

    private var _picture = MutableStateFlow<Picture?>(null)
    var picture: StateFlow<Picture?> = _picture

    private val compositeDisposable = CompositeDisposable()

    fun loadAll() {
        val (lat, lon) = _location
        if (lat in MIN_LATITUDE..MAX_LATITUDE && lon in MIN_LONGITUDE..MAX_LONGITUDE) {
            loadWeather(lat.toString(), lon.toString())
            checkError()
        }
    }

    fun setLocation(latNew: Double, lonNew: Double) {
        val (lat, lon) = _location
        if (lat !in latNew - 0.01..latNew + 0.01 || lon !in lonNew - 0.01..lonNew + 0.01)
            _location = (Pair(latNew, lonNew))
        loadAll()
    }

    private fun loadWeather(lat: String, lon: String) {
        _isLoading.value = true
        loadWeatherTodayUseCase.invoke(lat, lon)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                onTodayWeatherLoaded(response.first)
                _weatherWeek.value = response.second.toWeatherWeek()
                    .subList(0, response.second.toWeatherWeek().size - 3)
                _weatherToday.value = listOf(response.second.toWeatherWeek()[0])
            }
            .also { compositeDisposable.add(it) }
        _isLoading.value = false
    }

    private fun checkError() {
        if (_errorBus.toString() == R.string.error_network_text.toString()) _errorBus.tryEmit(R.string.error.toString())
    }

    private fun onTodayWeatherLoaded(response: WeatherTodayResponse) {
        val weatherToday = response.toWeatherToday()
        _temperatureToday.value = weatherToday.temp
        _mainToday.value = weatherToday.main
        _currentCity.value = weatherToday.city
        _currentCountry.value = weatherToday.country
        _picture.value = weatherToday.picture
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
