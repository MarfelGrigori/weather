package com.example.weatherapplication

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.weatherapplication.screens.home.HomeFragment
import junit.framework.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric

@RunWith(AndroidJUnit4::class)
class FragmentTest {

    @Test
    fun testLaunch() {
        val mActivityTestRule: ActivityTestRule<TestActivity> =
            ActivityTestRule(TestActivity::class.java)
        var mActivity: TestActivity? = null
        mActivity = mActivityTestRule.activity
        Robolectric.setupActivity(mActivity!!::class.java)
        val container =
            mActivity.findViewById<ConstraintLayout>(R.id.test_fragment_container_view_tag)
        val fragment = HomeFragment()
        assertNotNull(container)
        mActivity.supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view_tag, fragment)
            .addToBackStack(null)
            .commitAllowingStateLoss()
        fragment.view
        assertNotNull(fragment)
    }
}