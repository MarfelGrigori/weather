package com.example.weatherapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.weatherapplication.networking.weather.WeatherApi
import com.example.weatherapplication.repository.WeatherRepository
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


class ViewModelTests{
    @Mock
    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var isLoadingLiveData: LiveData<Boolean>

    @Mock
    private lateinit var observer: Observer<in Boolean>

    // 3

    val api = WeatherApi()
    val repository = WeatherRepository(api)
val first = LoadWeatherDayUseCase(repository)
val second = LoadWeatherWeekUseCase(repository)
val third = LoadWeatherTodayUseCase(repository)
    // 4
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = spy(MainViewModel(first,third,second))
        isLoadingLiveData = viewModel.isLoading
    }
    @Test
    fun `Verify livedata values changes on event`() {
        assertNotNull(viewModel.loadAll())
        viewModel.isLoading.observeForever(observer)
        verify(observer).onChanged(true)
        viewModel.loadAll()

    }


}