package com.realtomjoney.pyxlmoose.activities.canvas

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.longClick
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
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
class CanvasActivityTestsWhenColorPickerFragmentShown {
    @get:Rule
    var activityTestRule = ActivityScenarioRule(CanvasActivity::class.java)

    @Before
    fun uitest_setUp() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
    }

    @Test fun uitest_fragmentColorPickerRootLayout_childViews_areDisplayed() {
        for (id in EspressoUtilities.getColorPickerFragmentChildElementIds()) onView(withId(id)).check(matches(isDisplayed()))
    }
}
