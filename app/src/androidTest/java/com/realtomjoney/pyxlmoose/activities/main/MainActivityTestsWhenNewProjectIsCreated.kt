package com.realtomjoney.pyxlmoose.activities.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Rule
import org.junit.Test
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.realtomjoney.pyxlmoose.AndroidTestUtilityFunctions
import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.utility.EspressoUtilities
import org.junit.Before
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTestsWhenNewProjectIsCreated {
    @get:Rule
    var activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Before fun uitest_setup() {
        AndroidTestUtilityFunctions.createNewProject()
    }

    @Test fun uitest_activityCanvasRootLayout_childViews_doNotExist() {
        for (id in EspressoUtilities.getActivityCanvasRootLayoutChildElementIds()) onView(withId(id)).check(matches(isDisplayed()))
    }

    @Test fun uitest_fullscreenMenuItem_isDisplayed() {
        onView(withId(R.id.fullscreen)).check(matches(isDisplayed()))
    }

    @Test fun uitest_undoMenuItem_isDisplayed() {
        onView(withId(R.id.undo)).check(matches(isDisplayed()))
    }

    @Test fun uitest_zoomIn_isDisplayed() {
        onView(withId(R.id.zoom_in)).check(matches(isDisplayed()))
    }

    @Test fun uitest_zoomOut_isDisplayed() {
        onView(withId(R.id.zoom_out)).check(matches(isDisplayed()))
    }

    @Test fun uitest_saveProject_isDisplayed() {
        onView(withId(R.id.save_project)).check(matches(isDisplayed()))
    }
}
