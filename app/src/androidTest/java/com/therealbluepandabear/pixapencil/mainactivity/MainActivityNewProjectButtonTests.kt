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

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityNewProjectButtonTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    // Last successful test completion: 04-30 17:54 (API 32)
    // Last successful test completion on API 32: 04-30 17:54
    @Test
    fun clickButton() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
    }

    // Last successful test completion: 04-30 17:53 (API 32)
    // Last successful test completion on API 32: 04-30 17:53
    @Test
    fun clickButton25Times() {
        for (i in 0..25) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            pressBack()
        }
    }

    // Last successful test completion: 04-30 17:54 (API 32)
    // Last successful test completion on API 32: 04-30 17:54
    @Test
    fun clickButton50Times() {
        for (i in 0..50) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            pressBack()
        }
    }

    // Last successful test completion: 04-30 17:55 (API 32)
    // Last successful test completion on API 32: 04-30 17:55
    @Test
    fun clickButton100Times() {
        for (i in 0..100) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            pressBack()
        }
    }
}

