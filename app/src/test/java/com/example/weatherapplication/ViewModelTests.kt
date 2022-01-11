package com.example.weatherapplication

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weatherapplication.networking.weather.WeatherApi
import com.example.weatherapplication.repository.WeatherRepository
import com.example.weatherapplication.screens.home.viewmodel.HomeViewModel
import com.example.weatherapplication.useCases.LoadWeatherTodayUseCase
import com.example.weatherapplication.useCases.LoadWeatherWeekUseCase
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.flow.StateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


class ViewModelTests {
    @Mock
    private lateinit var viewModel: HomeViewModel

    @Mock
    private lateinit var isLoading: StateFlow<Boolean?>

    private val api = WeatherApi()
    private val repository = WeatherRepository(api)

    // 4
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = spy(HomeViewModel(LoadWeatherTodayUseCase(repository), LoadWeatherWeekUseCase(repository)))
        isLoading = viewModel.isLoading
    }

    @Test
    fun `Verify livedata values changes on event`() {
        assertNotNull(viewModel.loadAll())
        assertNotNull(isLoading)
        assertNotNull(viewModel.currentCity)
        assertNotNull(viewModel.currentCountry)
        assertNotNull(viewModel.weatherWeek)
        assertNotNull(viewModel.weatherToDay)
        assertNotNull(viewModel.mainToday)
        assertNotNull(viewModel.temperatureToday)
        assertNotNull(viewModel.temperatureToday)
    }

    @Test
    fun `set location test`() {
assertNotNull(viewModel.setLocation(0.0,0.0))
    }
}