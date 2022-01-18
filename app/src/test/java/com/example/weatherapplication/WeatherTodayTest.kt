package com.example.weatherapplication

import com.example.weatherapplication.domain.screens.home.useCase.entities.WeatherToday
import com.example.weatherapplication.utils.Picture
import org.junit.After
import org.junit.Assert
import org.junit.Test


class WeatherTodayTest {

    var weatherTodayEntity: WeatherToday? =
        WeatherToday("Hrodna", "BY", "228", "Clear", 100, 0.01, 0.0, 1001, 1.1, "W",Picture.SNOW)

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
        Assert.assertEquals("228", weatherTodayEntity?.temp)
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