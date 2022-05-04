package com.therealbluepandabear.pixapencil.canvasactivity.fragmenttests.colorpicker.colorpickerfragment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.longClick
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.fragments.colorpicker.hexFragmentInstance
import com.therealbluepandabear.pixapencil.fragments.colorpicker.pickerFragmentInstance
import com.therealbluepandabear.pixapencil.fragments.colorpicker.rgbFragmentInstance
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class CanvasActivityFragmentColorPickerTabTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(CanvasActivity::class.java)

    private fun pressRGBTab() {
        onView(withText(R.string.fragmentColorPicker_tab_rgb_str)).perform(longClick())
    }

    private fun pressHexTab() {
        onView(withText(R.string.fragmentColorPicker_tab_hex_str)).perform(longClick())
    }

    private fun pressPickerTab() {
        onView(withText(R.string.fragmentColorPicker_tab_color_picker_str)).perform(longClick())
    }

    @Before
    fun setup() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
    }

    // RGB tab

    @Test
    fun checkRGBFragmentRootLayout_IsDisplayed_InRGBTab() {
        pressRGBTab()
        onView(withId(R.id.fragmentRGBColorPicker_rootLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkRGBFragment_IsDisplayed_InRGBTab() {
        pressRGBTab()
        onView(withId(rgbFragmentInstance!!.requireView().id)).check(matches(isDisplayed()))
    }

    // Hexadecimal tab

    @Test
    fun checkHexFragmentRootLayout_IsDisplayed_InHexTab() {
        pressHexTab()
        onView(withId(R.id.fragmentHexadecimalColorPicker_rootLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkHexFragment_IsDisplayed_InHexTab() {
        pressHexTab()
        onView(withId(hexFragmentInstance!!.requireView().id)).check(matches(isDisplayed()))
    }

    // Picker tab

    @Test
    fun checkPickerFragmentRootLayout_IsDisplayed_InPickerTab() {
        pressPickerTab()
        onView(withId(R.id.fragmentColorPickerPicker_rootLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkPickerFragment_IsDisplayed_InPickerTab() {
        pressPickerTab()
        onView(withId(pickerFragmentInstance!!.requireView().id)).check(matches(isDisplayed()))
    }
}