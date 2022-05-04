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