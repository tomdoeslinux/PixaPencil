package com.therealbluepandabear.pixapencil.canvasactivity.fragmenttests.colorpicker

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.longClick
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
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
class CanvasActivityFragmentColorPickerPickerViewDisplayedTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(CanvasActivity::class.java)

    @Before
    fun setup() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
    }

    @Test
    fun checkRootLayoutIsDisplayed() {
        onView(withId(R.id.fragmentColorPickerPicker_rootLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkColorPickerViewIsDisplayed() {
        onView(withId(R.id.fragmentColorPickerPicker_colorPickerView)).check(matches(isDisplayed()))
    }

    @Test
    fun checkColorPreviewIsDisplayed() {
        onView(withId(R.id.fragmentColorPickerPicker_colorPreview)).check(matches(isDisplayed()))
    }

    @Test
    fun checkDoneButtonIsDisplayed() {
        onView(withId(R.id.fragmentColorPickerPicker_doneButton)).check(matches(isDisplayed()))
    }

    @Test
    fun checkRootLayoutIsDisplayedWhenPickerTabIsPressed() {
        onView(withText(R.string.fragmentColorPicker_tab_color_picker_str)).perform(longClick())
        onView(withId(R.id.fragmentColorPickerPicker_rootLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkColorPickerViewIsDisplayedWhenPickerTabIsPressed() {
        onView(withText(R.string.fragmentColorPicker_tab_color_picker_str)).perform(longClick())
        onView(withId(R.id.fragmentColorPickerPicker_colorPickerView)).check(matches(isDisplayed()))
    }

    @Test
    fun checkColorPreviewIsDisplayedWhenPickerTabIsPressed() {
        onView(withText(R.string.fragmentColorPicker_tab_color_picker_str)).perform(longClick())
        onView(withId(R.id.fragmentColorPickerPicker_colorPreview)).check(matches(isDisplayed()))
    }

    @Test
    fun checkDoneButtonIsDisplayedWhenPickerTabIsPressed() {
        onView(withText(R.string.fragmentColorPicker_tab_color_picker_str)).perform(longClick())
        onView(withId(R.id.fragmentColorPickerPicker_doneButton)).check(matches(isDisplayed()))
    }
}