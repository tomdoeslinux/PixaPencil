package com.therealbluepandabear.pixapencil.canvasactivity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test completion summary for `CanvasActivityPrimarySecondaryColorIndicatorTests`:
 *
 * **Last completion of tests in this package file:**
 *
 * - 2022-05-02 20:59 (5/5 passed) on API 32
 *
 * **Last completion of tests in this package file for API 32:**
 * - 2022-05-02 20:59 (5/5 passed)
 */

@RunWith(AndroidJUnit4::class)
@LargeTest
class CanvasActivityPrimarySecondaryColorIndicatorTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(CanvasActivity::class.java)

    @Test
    fun checkPrimaryViewIndicatorIsDisplayedByDefault() {
        onView(withId(R.id.activityCanvas_colorPrimaryViewIndicator)).check(matches(isDisplayed()))
    }

    @Test
    fun checkPrimaryViewIndicatorIsDisplayedWhenPrimaryColorClicked() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(click())
        onView(withId(R.id.activityCanvas_colorPrimaryViewIndicator)).check(matches(isDisplayed()))
    }

    @Test
    fun checkSecondaryViewIndicatorIsDisplayedWhenSecondaryColorClicked() {
        onView(withId(R.id.activityCanvas_colorSecondaryView)).perform(click())
        onView(withId(R.id.activityCanvas_colorSecondaryViewIndicator)).check(matches(isDisplayed()))
    }

    @Test
    fun checkPrimaryIndicatorIsDisplayedWhenSecondaryColorIsClickedAndThenPrimaryColorIsClicked() {
        onView(withId(R.id.activityCanvas_colorSecondaryView)).perform(click())
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(click())
        onView(withId(R.id.activityCanvas_colorPrimaryViewIndicator)).check(matches(isDisplayed()))
    }

    @Test
    fun checkSecondaryIndicatorIsDisplayedWhenSecondaryColorIsClickedAndThenPrimaryColorIsClickedAndThenSecondaryColorIsClicked() {
        onView(withId(R.id.activityCanvas_colorSecondaryView)).perform(click())
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(click())
        onView(withId(R.id.activityCanvas_colorSecondaryView)).perform(click())
        onView(withId(R.id.activityCanvas_colorSecondaryViewIndicator)).check(matches(isDisplayed()))
    }
}