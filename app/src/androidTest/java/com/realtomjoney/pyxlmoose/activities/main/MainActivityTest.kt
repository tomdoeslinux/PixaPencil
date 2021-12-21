package com.realtomjoney.pyxlmoose.activities.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.realtomjoney.pyxlmoose.utility.EspressoUtilities

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    @get:Rule
    var activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test fun uitest_activityMainRootLayout_childViews_areDisplayed() {
        for (id in EspressoUtilities.getActivityMainRootLayoutChildElementIds()) onView(withId(id)).check(matches(isDisplayed()))
    }
}
