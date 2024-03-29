package com.example.weatherapplication.weatherDay.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weatherapplication.common.repository.WeatherServerImpl
import com.example.weatherapplication.weatherDay.useCases.loadWeather.LoadWeatherUseCaseImpl
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.spy
import org.mockito.MockitoAnnotations

class WeatherDayViewModelTest {

    @Mock
    private lateinit var viewModel: WeatherDayViewModel

    private val api = com.example.weatherapplication.common.networking.weather.WeatherApi()
    private val repository = WeatherServerImpl(api)
    private val first = LoadWeatherUseCaseImpl(repository)

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