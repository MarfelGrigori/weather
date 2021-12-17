package com.example.weatherapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.weatherapplication.screens.home.viewmodel.MainViewModel
import com.example.weatherapplication.useCases.LoadWeatherTodayUseCase
import com.example.weatherapplication.useCases.LoadWeatherWeekUseCase
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
    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var isLoading: StateFlow<Boolean?>

    private val first = mock(LoadWeatherWeekUseCase::class.java)
    private val second = mock(LoadWeatherTodayUseCase::class.java)

    // 4
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = spy(MainViewModel(second, first))
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