package com.example.weatherapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.weatherapplication.networking.WeatherService
import com.example.weatherapplication.networking.weather.WeatherApi
import com.example.weatherapplication.repository.WeatherRepository
import com.example.weatherapplication.screens.first.entities.WeatherToday
import com.example.weatherapplication.useCases.LoadWeather5DayUseCase
import com.example.weatherapplication.useCases.LoadWeatherTodayUseCase
import com.example.weatherapplication.useCases.LoadWeatherWeekUseCase
import com.example.weatherapplication.viewModel.MainViewModel
import junit.framework.Assert.*
import net.bytebuddy.matcher.ElementMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import javax.inject.Inject


@RunWith(AndroidJUnit4::class)
class ViewModelTest {

    // 2
    @Mock
    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var isLoadingLiveData: LiveData<Boolean>

    @Mock
    private lateinit var observer: Observer<in Boolean>
    @Mock
    var api = WeatherApi()
    // 3
    lateinit var first : LoadWeather5DayUseCase

    lateinit var second : LoadWeatherTodayUseCase

   lateinit var third : LoadWeatherWeekUseCase

    // 4
    @Before
    fun setup() {
        first = mock(LoadWeather5DayUseCase::class.java)
        second = mock(LoadWeatherTodayUseCase::class.java)
        third = mock(LoadWeatherWeekUseCase::class.java)
        MockitoAnnotations.initMocks(this)
        viewModel = spy(MainViewModel(first,second,third))
        isLoadingLiveData = viewModel.isLoading
    }
    @Test
    fun `Verify livedata values changes on event`() {
        assertNotNull(viewModel.loadAll())
        verify(observer).onChanged(false)
        viewModel.loadAll()
        verify(observer).onChanged(true)
    }
    @Test
    fun `Assert loading values are correct fetching quotes`() {
        // 1
        val testQuote = WeatherToday("Hrodna","BY",12,"Clouds",100,0.0,0.0,1001,1.12,"W")
        // 2
        var isLoading = isLoadingLiveData.value
        // 3
        assertNotNull(isLoading)
        // 4
        isLoading?.let { assertTrue(it) }
        viewModel.loadAll()
        // 6
        isLoading = isLoadingLiveData.value
        assertNotNull(isLoading)
        isLoading?.let { assertFalse(it) }
    }
    @Test
    fun `when loadData() is called, should load data`() {
        viewModel.loadAll()
       val expected = "Hrodna"
        assertThat(viewModel.currentCity.value, viewModel.currentCity.value==(expected))
    }

}