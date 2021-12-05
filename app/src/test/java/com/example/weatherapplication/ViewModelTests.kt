package com.example.weatherapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.weatherapplication.useCases.LoadWeatherDayUseCase
import com.example.weatherapplication.useCases.LoadWeatherTodayUseCase
import com.example.weatherapplication.useCases.LoadWeatherWeekUseCase
import com.example.weatherapplication.viewModel.MainViewModel
import junit.framework.Assert.assertNotNull
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
    private lateinit var isLoadingLiveData: LiveData<Boolean>

    @Mock
    private lateinit var observer: Observer<in Boolean>

    // 3

    private val first = mock(LoadWeatherDayUseCase::class.java)
    private val second = mock(LoadWeatherWeekUseCase::class.java)
    private val third = mock(LoadWeatherTodayUseCase::class.java)

    // 4
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = spy(MainViewModel(first, third, second))
        isLoadingLiveData = viewModel.isLoading
    }

    @Test
    fun `Verify livedata values changes on event`() {
        assertNotNull(viewModel.loadAll())
        viewModel.isLoading.observeForever(observer)
        verify(observer).onChanged(true)
    }

    @Test
    fun `verify livedata city value changes on event`() {
        assertNotNull(viewModel.loadAll())
        assertNotNull(viewModel.currentCity)
    }

    @Test
    fun `verify livedata country value changes on event`() {
        assertNotNull(viewModel.loadAll())
        assertNotNull(viewModel.currentCountry)
    }

    @Test
    fun `verify livedata weather week value changes on event`() {
        assertNotNull(viewModel.loadAll())
        assertNotNull(viewModel.weatherWeek)
    }

    @Test
    fun `verify livedata weather day value changes on event`() {
        assertNotNull(viewModel.loadAll())
        assertNotNull(viewModel.weatherToDay)
    }

    @Test
    fun `verify livedata weatherToday value changes on event`() {
        assertNotNull(viewModel.loadAll())
        assertNotNull(viewModel.mainToday)
    }

    @Test
    fun `verify livedata temperatureToday value changes on event`() {
        assertNotNull(viewModel.loadAll())
        assertNotNull(viewModel.temperatureToday)
    }
}