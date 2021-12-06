package com.realtomjoney.pyxlmoose.activities.`2_canvas`

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.realtomjoney.pyxlmoose.R
import org.junit.Rule
import org.junit.runner.RunWith
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.realtomjoney.pyxlmoose.activities.canvas.CanvasActivity
import org.junit.Test


@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class CanvasActivityTestsOnPrimaryOrSecondaryColorLongTap {
    @get:Rule
    var activityTestRule = ActivityScenarioRule(CanvasActivity::class.java)

    private fun longTapPrimaryColor() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
    }

    private fun longTapSecondaryColor() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
    }

    @Test fun userCanLongTapOnPrimaryColor() {
        longTapPrimaryColor()
    }

    @Test fun userCanLongTapOnSecondaryColor() {
        longTapSecondaryColor()
    }

    @Test fun uitest_doneButton_isNotDisplayed_after_primaryColor_isLongPressed() {
        longTapPrimaryColor()
        onView(withId(R.id.activityCanvas_doneButton)).check(ViewAssertions.matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test fun uitest_colorPickerRecyclerView_isNotDisplayed_after_primaryColor_isLongPressed() {
        longTapPrimaryColor()
        onView(withId(R.id.activityCanvas_colorPickerRecyclerView)).check(ViewAssertions.matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test fun uitest_titleTextView_isNotDisplayed_after_primaryColor_isLongPressed() {
        longTapPrimaryColor()
        onView(withId(R.id.activityCanvas_canvasTitleEditText)).check(ViewAssertions.matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test fun uitest_colorSwapButton_isNotDisplayed_after_primaryColor_isLongPressed() {
        longTapPrimaryColor()
        onView(withId(R.id.activityCanvas_colorSwapButton)).check(ViewAssertions.matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test fun uitest_colorPrimarySelected_isNotDisplayed_after_primaryColor_isLongPressed() {
        longTapPrimaryColor()
        onView(withId(R.id.activityCanvas_colorPrimaryView)).check(ViewAssertions.matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test fun uitest_colorSecondarySelected_isNotDisplayed_after_primaryColor_isLongPressed() {
        longTapPrimaryColor()
        onView(withId(R.id.activityCanvas_colorSecondaryView)).check(ViewAssertions.matches(withEffectiveVisibility(Visibility.GONE)))
    }
}