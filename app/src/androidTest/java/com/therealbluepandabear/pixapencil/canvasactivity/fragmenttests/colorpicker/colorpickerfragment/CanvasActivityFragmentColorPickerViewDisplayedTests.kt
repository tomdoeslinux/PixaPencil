package com.therealbluepandabear.pixapencil.canvasactivity.fragmenttests.colorpicker.colorpickerfragment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.longClick
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.colorPickerFragmentInstance
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test completion summary for `CanvasActivityColorPickerFragmentTests`:
 *
 * **Last completion of tests in this package file:**
 *
 * - 2022-05-02 20:59 (12/12 passed) on API 32
 *
 * **Last completion of tests in this package file for API 32:**
 * - 2022-05-02 20:59 (12/12 passed)
 */

@RunWith(AndroidJUnit4::class)
@LargeTest
class CanvasActivityFragmentColorPickerViewDisplayedTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(CanvasActivity::class.java)

    private fun longClickPrimaryColor() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
    }

    private fun longClickSecondaryColor() {
        onView(withId(R.id.activityCanvas_colorSecondaryView)).perform(longClick())
    }

    // Test cases when user presses the primary color

    @Test
    fun checkRootLayout_IsDisplayed_OnPrimaryColor() {
        longClickPrimaryColor()
        onView(withId(R.id.fragmentColorPicker_rootLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkFragment_IsDisplayed_OnPrimaryColor() {
        longClickPrimaryColor()
        onView(withId(colorPickerFragmentInstance.requireView().id)).check(matches(isDisplayed()))
    }

    @Test
    fun checkPrimaryFragmentHost_IsDisplayed_OnPrimaryColor() {
        longClickPrimaryColor()
        onView(withId(R.id.fragmentColorPicker_primaryFragmentHost)).check(matches(isDisplayed()))
    }

    @Test
    fun checkTabLayout_IsDisplayed_OnPrimaryColor() {
        longClickPrimaryColor()
        onView(withId(R.id.fragmentColorPicker_tabLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkTabLayoutFragmentHost_IsDisplayed_OnPrimaryColor() {
        longClickPrimaryColor()
        onView(withId(R.id.fragmentColorPicker_tabLayoutFragmentHost)).check(matches(isDisplayed()))
    }

    // Test cases when user presses the secondary color

    @Test
    fun checkRootLayout_IsDisplayed_OnSecondaryColor() {
        longClickSecondaryColor()
        onView(withId(R.id.fragmentColorPicker_rootLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkFragment_IsDisplayed_OnSecondaryColor() {
        longClickSecondaryColor()
        onView(withId(colorPickerFragmentInstance.requireView().id)).check(matches(isDisplayed()))
    }

    @Test
    fun checkPrimaryFragmentHost_IsDisplayed_OnSecondaryColor() {
        longClickSecondaryColor()
        onView(withId(R.id.fragmentColorPicker_primaryFragmentHost)).check(matches(isDisplayed()))
    }

    @Test
    fun checkTabLayout_IsDisplayed_OnSecondaryColor() {
        longClickSecondaryColor()
        onView(withId(R.id.fragmentColorPicker_tabLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkTabLayoutFragmentHost_IsDisplayed_OnSecondaryColor() {
        longClickSecondaryColor()
        onView(withId(R.id.fragmentColorPicker_tabLayoutFragmentHost)).check(matches(isDisplayed()))
    }
}
