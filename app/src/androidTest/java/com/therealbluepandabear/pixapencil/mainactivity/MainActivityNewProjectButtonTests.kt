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

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityNewProjectButtonTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun clickOnNewProjectButton() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
    }

    @Test
    fun clickOnNewProjectButton25Times() {
        for (i in 0..25) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            pressBack()
        }
    }

    @Test
    fun clickOnNewProjectButton50Times() {
        for (i in 0..50) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            pressBack()
        }
    }

    @Test
    fun clickOnNewProjectButton100Times() {
        for (i in 0..100) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            pressBack()
        }
    }

}

