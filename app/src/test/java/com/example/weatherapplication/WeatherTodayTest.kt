package com.example.weatherapplication

import com.example.weatherapplication.screens.first.entities.WeatherToday
import org.junit.After
import org.junit.Assert

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.lang.Exception


class WeatherTodayTest {
    private val city: String = "Barysaw"
    private val country: String = "BY"
    private val temp: Int = 228
    private val main: String = "Clear"
    private val humidity: Int = 100
    private val rain: Double = 0.01
    private val snow: Double = 0.0
    private val pressure: Int = 1001
    private val speed: Double = 1.1
    private val deg: String ="W"

    @Mock
    var weatherTodayEntity: WeatherToday? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(weatherTodayEntity?.city).thenReturn(city)
        Mockito.`when`(weatherTodayEntity?.country).thenReturn(country)
        Mockito.`when`(weatherTodayEntity?.temp).thenReturn(temp)
        Mockito.`when`(weatherTodayEntity?.main).thenReturn(main)
        Mockito.`when`(weatherTodayEntity?.humidity).thenReturn(humidity)
        Mockito.`when`(weatherTodayEntity?.rain).thenReturn(rain)
        Mockito.`when`(weatherTodayEntity?.snow).thenReturn(snow)
        Mockito.`when`(weatherTodayEntity?.pressure).thenReturn(pressure)
        Mockito.`when`(weatherTodayEntity?.speed).thenReturn(speed)
        Mockito.`when`(weatherTodayEntity?.deg).thenReturn(deg)
    }

    @Test
    fun testCity() {
        Mockito.`when`(weatherTodayEntity?.city).thenReturn(city)
        Assert.assertEquals("Minsk", weatherTodayEntity?.city)
    }

    @Test
    fun testCountry() {
        Mockito.`when`(weatherTodayEntity?.country).thenReturn(country)
        Assert.assertEquals("BY", weatherTodayEntity?.country)
    }

    @Test
    fun testTemp() {
        Mockito.`when`(weatherTodayEntity?.temp).thenReturn(temp)
        Assert.assertEquals(228, weatherTodayEntity?.temp)
    }

    @Test
    fun testMain() {
        Mockito.`when`(weatherTodayEntity?.main).thenReturn(main)
        Assert.assertEquals("Clouds", weatherTodayEntity?.main)
    }

    @Test
    fun testHumidity() {
        Mockito.`when`(weatherTodayEntity?.humidity).thenReturn(humidity)
        Assert.assertEquals(100, weatherTodayEntity?.humidity)
    }



    @Test
    fun testRain() {
        Mockito.`when`(weatherTodayEntity?.rain).thenReturn(rain)
        Assert.assertNotEquals(0.0, weatherTodayEntity?.rain)
    }

    @Test
    fun testSnow() {
        Mockito.`when`(weatherTodayEntity?.snow).thenReturn(snow)
        Assert.assertNotEquals(0.0, weatherTodayEntity?.snow)
    }

    @Test
    fun testPressure() {
        Mockito.`when`(weatherTodayEntity?.pressure).thenReturn(pressure)
        Assert.assertNotEquals(1001, weatherTodayEntity?.pressure)
    }

    @Test
    fun testSpeed() {
        Mockito.`when`(weatherTodayEntity?.speed).thenReturn(speed)
        Assert.assertNotEquals(11.1, weatherTodayEntity?.speed)
    }

    @Test
    fun testDeg() {
        Mockito.`when`(weatherTodayEntity?.deg).thenReturn(deg)
        Assert.assertNotEquals("W", weatherTodayEntity?.deg)
    }


    @After
    @Throws(Exception::class)
    fun tearDown() {
        weatherTodayEntity = null
    }
}