package com.therealbluepandabear.pixapencil.mainactivity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

// Note:
// All times in ESPRESSO TEST DOCUMENTATION are in NZST

/**
 * Test completion summary for `MainActivityNewProjectButtonTests`:
 *
 * **Last completion of tests in this package file:**
 *
 * - 2022-05-02 21:46 (4/4 passed) on API 32
 *
 * **Last completion of tests in this package file for API 32:**
 * - 2022-05-02 21:46 (4/4 passed)
 */

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityNewProjectButtonTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun clickButton() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
    }

    @Test
    fun clickButton25Times() {
        for (i in 0..25) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            pressBack()
        }
    }

    @Test
    fun clickButton50Times() {
        for (i in 0..50) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            pressBack()
        }
    }

    @Test
    fun clickButton100Times() {
        for (i in 0..100) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            pressBack()
        }
    }
}

