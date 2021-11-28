package com.realtomjoney.pyxlmoose.activities.canvas

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.realtomjoney.pyxlmoose.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner


@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class CanvasActivityTest {
    @get:Rule
    var activityTestRule = ActivityTestRule(CanvasActivity::class.java)

    @Test
    fun uitest_doneButtonFAB_isDisplayed() {
        onView(withId(R.id.doneButton)).check(matches(isDisplayed()))
    }

    @Test
    fun uitest_colourPickerRecyclerView_isDisplayed() {
        onView(withId(R.id.colourPickerRecyclerView)).check(matches(isDisplayed()))
    }
}
