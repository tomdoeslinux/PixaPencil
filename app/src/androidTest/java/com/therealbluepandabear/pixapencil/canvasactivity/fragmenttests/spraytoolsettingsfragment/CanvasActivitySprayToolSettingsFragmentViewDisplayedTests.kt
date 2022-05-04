package com.therealbluepandabear.pixapencil.canvasactivity.fragmenttests.spraytoolsettingsfragment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class CanvasActivitySprayToolSettingsFragmentViewDisplayedTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(CanvasActivity::class.java)

    @Before
    fun setup() {
        onView(withId(R.id.fragmentTools_sprayButton_h)).perform(scrollTo(), click(), click())
    }

    @Test
    fun checkRadiusTextInputEditText_IsDisplayed() {
        onView(withId(R.id.fragmentSprayToolSettings_radiusTextInputEditText)).check(matches(isDisplayed()))
    }

    @Test
    fun checkRadiusTextInputLayout_IsDisplayed() {
        onView(withId(R.id.fragmentSprayToolSettings_radiusTextInputLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkStrengthTextInputEditText_IsDisplayed() {
        onView(withId(R.id.fragmentSprayToolSettings_strengthTextInputEditText)).check(matches(isDisplayed()))
    }

    @Test
    fun checkStrengthTextInputLayout_IsDisplayed() {
        onView(withId(R.id.fragmentSprayToolSettings_strengthTextInputLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkDoneButton_IsDisplayed() {
        onView(withId(R.id.fragmentSprayToolSettings_doneButton)).check(matches(isDisplayed()))
    }
}