package com.example.weatherapplication

import com.example.weatherapplication.screens.home.entities.WeatherToday
import org.junit.After
import org.junit.Assert

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
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


    var weatherTodayEntity: WeatherToday? = WeatherToday("Hrodna","BY",228,"Clear",100,0.01,0.0,1001,1.1,"W")

    @Test
    fun testCity() {
        Assert.assertEquals("Hrodna", weatherTodayEntity?.city)
    }

    @Test
    fun testCountry() {
        Assert.assertEquals("BY", weatherTodayEntity?.country)
    }

    @Test
    fun testTemp() {
        Assert.assertEquals(228, weatherTodayEntity?.temp)
    }

    @Test
    fun testMain() {
        Assert.assertEquals("Clear", weatherTodayEntity?.main)
    }

    @Test
    fun testHumidity() {
        Assert.assertEquals(100, weatherTodayEntity?.humidity)
    }



    @Test
    fun testRain() {
        Assert.assertNotEquals(0.0, weatherTodayEntity?.rain)
    }

    @Test
    fun testSnow() {
        Assert.assertNotEquals(1.0, weatherTodayEntity?.snow)
    }

    @Test
    fun testPressure() {
        Assert.assertNotEquals(1006, weatherTodayEntity?.pressure)
    }

    @Test
    fun testSpeed() {
        Assert.assertNotEquals(11.1, weatherTodayEntity?.speed)
    }

    @Test
    fun testDeg() {
        Assert.assertNotEquals("aboba", weatherTodayEntity?.deg)
    }


    @After
    @Throws(Exception::class)
    fun tearDown() {
        weatherTodayEntity = null
    }
}