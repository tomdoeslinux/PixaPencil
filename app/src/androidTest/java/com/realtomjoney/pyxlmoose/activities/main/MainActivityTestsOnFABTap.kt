package com.realtomjoney.pyxlmoose.activities.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import com.realtomjoney.pyxlmoose.R
import org.junit.Rule
import org.junit.Test
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.realtomjoney.pyxlmoose.utility.EspressoUtilities
import org.junit.Before
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTestsOnFABTap {
    @get:Rule
    var activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun uitest_setUp() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
    }

    @Test fun uitest_fragmentNewCanvasRootLayout_childViews_areDisplayed() {
        for (id in EspressoUtilities.getNewCanvasFragmentChildElementIds()) onView(withId(id)).check(matches(isDisplayed()))
    }

    @Test fun uitest_activityMainRootLayout_childViews_haveGoneEffectiveVisibility() {
        for (id in EspressoUtilities.getActivityMainRootLayoutChildElementIds()) if (id != R.id.activityMain_primaryFragmentHost) onView(withId(id)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }
}
