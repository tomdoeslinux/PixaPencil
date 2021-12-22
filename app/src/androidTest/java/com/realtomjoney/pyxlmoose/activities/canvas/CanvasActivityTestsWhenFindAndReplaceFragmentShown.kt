package com.realtomjoney.pyxlmoose.activities.canvas

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.utility.EspressoUtilities
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class CanvasActivityTestsWhenFindAndReplaceFragmentShown {
    @get:Rule
    var activityTestRule = ActivityScenarioRule(CanvasActivity::class.java)

    @Before
    fun uitest_setUp() {
        onView(withId(R.id.fragmentTools_findAndReplaceButton)).perform(scrollTo(), click())
    }

    @Test
    fun uitest_fragmentFindAndReplaceRootLayout_childViews_areDisplayed() {
        for (id in EspressoUtilities.getFindAndReplaceFragmentChildElementIds()) onView(withId(id)).check(matches(isDisplayed()))
    }
}
