package com.example.weatherapplication

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragment
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.weatherapplication.screens.home.HomeFragment
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class FragmentTest {
    @Test
    fun testEventFragment() {
        val fragmentArgs = bundleOf("numElements" to 0)
        val scenario = launchFragment<HomeFragment>(fragmentArgs)
    }
}