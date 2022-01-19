package com.example.weatherapplication

import com.example.weatherapplication.common.utils.Converter.degToWindRoze
import com.example.weatherapplication.common.utils.Converter.getDate
import com.example.weatherapplication.common.utils.Converter.getDay
import junit.framework.Assert.assertEquals
import org.junit.Test

class ConverterUtilTest {
    @Test
    fun testConvertWindDoze() {
        val actual: String = 228.degToWindRoze()
        val expected = "SW"
        assertEquals( "Test",expected ,actual)
    }

    @Test
    fun testGetDate() {
        val actual: String = 1638511677L.getDate("YYYY-MM-DD")
        val expected = "1970-01-20"
        assertEquals("get date", expected, actual)
    }
    @Test
    fun testGetDay() {
        val actual: String = 1638511677L.getDay()
        val expected = "Понедельник"
        assertEquals("get day", expected, actual)
    }

}