package com.example.weatherapplication.home.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weatherapplication.common.repository.WeatherServerImpl
import com.example.weatherapplication.home.useCase.loadWeather.LoadWeatherUseCaseImpl
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

    private val api = com.example.weatherapplication.common.networking.weather.WeatherApi()
    private val repository = WeatherServerImpl(api)

    // 4
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = spy(
            HomeViewModel(
                LoadWeatherUseCaseImpl(repository)
            )
        )
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
        assertNotNull(viewModel.setLocation(0.0, 0.0))
    }
}