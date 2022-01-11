package com.example.weatherapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weatherapplication.networking.weather.WeatherApi
import com.example.weatherapplication.repository.WeatherRepository
import com.example.weatherapplication.screens.weatherday.viewmodel.WeatherDayViewModel
import com.example.weatherapplication.useCases.LoadWeatherDayUseCase
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.spy
import org.mockito.MockitoAnnotations

class SecondViewModelTest {

    @Mock
    private lateinit var viewModel: WeatherDayViewModel

    private val api = WeatherApi()
    private val repository = WeatherRepository(api)
    private val first = LoadWeatherDayUseCase(repository)

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = spy(WeatherDayViewModel(first))
    }

    @Test
    fun `set location test`() {
        viewModel._location = Pair(0.0, 0.0)
        assertEquals(0.0, viewModel._location.first)
    }

    @Test
    fun `Verify livedata values changes on event`() {
        assertNotNull(viewModel.weatherToDay)
    }
}