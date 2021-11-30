package com.realtomjoney.pyxlmoose.activities.`2_canvas`

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.realtomjoney.pyxlmoose.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.realtomjoney.pyxlmoose.activities.canvas.CanvasActivity
import org.hamcrest.Matchers

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class CanvasActivityTestsOnFindAndReplaceButtonTap {
    @get:Rule
    var activityTestRule = ActivityScenarioRule(CanvasActivity::class.java)

    private fun goToFindAndReplaceFragment() {
        onView(withId(R.id.activityCanvasHorizontalScrollView)).perform(swipeLeft())
        onView(withId(R.id.findAndReplaceButton)).perform(click())
    }

    @Test
    fun uitest_doneButton_isNotDisplayed_after_findAndReplaceButton_isPressed() {
        goToFindAndReplaceFragment()
        onView(withId(R.id.doneButton)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun uitest_colourPickerRecyclerView_isNotDisplayed_after_findAndReplaceButton_isPressed() {
        goToFindAndReplaceFragment()
        onView(withId(R.id.colourPickerRecyclerView)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun uitest_titleTextView_isNotDisplayed_after_findAndReplaceButton_isPressed() {
        goToFindAndReplaceFragment()
        onView(withId(R.id.titleTextView)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun uitest_colorSwapButton_isNotDisplayed_after_findAndReplaceButton_isPressed() {
        goToFindAndReplaceFragment()
        onView(withId(R.id.colorSwapButton)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun uitest_colourPrimarySelected_isNotDisplayed_after_findAndReplaceButton_isPressed() {
        goToFindAndReplaceFragment()
        onView(withId(R.id.colourPrimarySelected)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun uitest_colourSecondarySelected_isNotDisplayed_after_findAndReplaceButton_isPressed() {
        goToFindAndReplaceFragment()
        onView(withId(R.id.colourSecondarySelected)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }
}