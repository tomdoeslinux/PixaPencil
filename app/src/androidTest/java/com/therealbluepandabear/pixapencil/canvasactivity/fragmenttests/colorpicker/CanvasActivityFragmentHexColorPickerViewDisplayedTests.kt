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
class CanvasActivityFragmentHexColorPickerViewDisplayedTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(CanvasActivity::class.java)

    @Before
    fun setup() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
        onView(withText(R.string.fragmentColorPicker_tab_hex_str)).perform(longClick())
    }

    @Test
    fun checkRootLayout_IsDisplayed() {
        onView(withId(R.id.fragmentHexadecimalColorPicker_rootLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkColorPreview_IsDisplayed() {
        onView(withId(R.id.fragmentHexadecimalColorPicker_colorPreview)).check(matches(isDisplayed()))
    }

    @Test
    fun checkHexadecimalValueTextInputEditText_IsDisplayed() {
        onView(withId(R.id.fragmentHexadecimalColorPicker_hexadecimalValueTextInputEditText)).check(matches(isDisplayed()))
    }

    @Test
    fun checkHexadecimalValueTextInputLayout_IsDisplayed() {
        onView(withId(R.id.fragmentHexadecimalColorPicker_hexadecimalValueTextInputLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkColorPickerDoneButton_IsDisplayed() {
        onView(withId(R.id.fragmentHexadecimalColorPicker_doneButton)).check(matches(isDisplayed()))
    }
}