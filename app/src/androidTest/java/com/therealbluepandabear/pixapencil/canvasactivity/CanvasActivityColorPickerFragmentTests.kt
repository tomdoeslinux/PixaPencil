package com.therealbluepandabear.pixapencil.canvasactivity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.longClick
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.colorPickerFragmentInstance
import com.therealbluepandabear.pixapencil.fragments.colorpicker.hexFragmentInstance
import com.therealbluepandabear.pixapencil.fragments.colorpicker.pickerFragmentInstance
import com.therealbluepandabear.pixapencil.fragments.colorpicker.rgbFragmentInstance
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
class CanvasActivityColorPickerFragmentTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(CanvasActivity::class.java)

    @Test
    fun checkColorPickerFragmentRootLayoutIsDisplayedWhenLongClickingOnPrimaryColor() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
        onView(withId(R.id.fragmentColorPicker_rootLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkColorPickerFragmentIsDisplayedWhenLongClickingOnPrimaryColor() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
        onView(withId(colorPickerFragmentInstance.requireView().id)).check(matches(isDisplayed()))
    }

    @Test
    fun checkColorPickerFragmentRootLayoutIsDisplayedWhenLongClickingOnSecondaryColor() {
        onView(withId(R.id.activityCanvas_colorSecondaryView)).perform(longClick())
        onView(withId(R.id.fragmentColorPicker_rootLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkColorPickerFragmentIsDisplayedWhenLongClickingOnSecondaryColor() {
        onView(withId(R.id.activityCanvas_colorSecondaryView)).perform(longClick())
        onView(withId(colorPickerFragmentInstance.requireView().id)).check(matches(isDisplayed()))
    }

    @Test
    fun checkColorPickerPickerFragmentRootLayoutIsDisplayedInsideColorPickerFragment() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
        onView(withId(R.id.fragmentColorPickerPicker_rootLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkColorPickerPickerFragmentIsDisplayedInsideColorPickerFragment() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
        onView(withId(pickerFragmentInstance!!.requireView().id)).check(matches(isDisplayed()))
    }

    @Test
    fun checkRGBFragmentIsDisplayedWhenRGBTabIsPressedInsideColorPickerFragment() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
        onView(withText(R.string.fragmentColorPicker_tab_rgb_str)).perform(longClick())
        onView(withId(rgbFragmentInstance!!.requireView().id)).check(matches(isDisplayed()))
    }

    @Test
    fun checkRGBFragmentRootLayoutIsDisplayedWhenRGBTabIsPressedInsideColorPickerFragment() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
        onView(withText(R.string.fragmentColorPicker_tab_rgb_str)).perform(longClick())
        onView(withId(R.id.fragmentRGBColorPicker_rootLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkHexFragmentIsDisplayedWhenHexTabIsPressedInsideColorPickerFragment() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
        onView(withText(R.string.fragmentColorPicker_tab_hex_str)).perform(longClick())
        onView(withId(hexFragmentInstance!!.requireView().id)).check(matches(isDisplayed()))
    }

    @Test
    fun checkHexFragmentRootLayoutIsDisplayedWhenHexTabIsPressedInsideColorPickerFragment() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
        onView(withText(R.string.fragmentColorPicker_tab_hex_str)).perform(longClick())
        onView(withId(R.id.fragmentHexadecimalColorPicker_rootLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkPickerFragmentIsDisplayedWhenPickerTabIsPressedInsideColorPickerFragment() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
        onView(withText(R.string.fragmentColorPicker_tab_color_picker_str)).perform(longClick())
        onView(withId(pickerFragmentInstance!!.requireView().id)).check(matches(isDisplayed()))
    }

    @Test
    fun checkPickerFragmentRootLayoutIsDisplayedInsideColorPickerFragment() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
        onView(withText(R.string.fragmentColorPicker_tab_color_picker_str)).perform(longClick())
        onView(withId(R.id.fragmentColorPickerPicker_rootLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkFragmentColorPickerPickerRootLayoutIsDisplayedByDefaultInsideColorPickerFragment() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
        onView(withId(R.id.fragmentColorPickerPicker_rootLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkFragmentColorPickerPickerColorPickerViewIsDisplayedByDefaultInsideColorPickerFragment() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
        onView(withId(R.id.fragmentColorPickerPicker_colorPickerView)).check(matches(isDisplayed()))
    }

    @Test
    fun checkFragmentColorPickerPickerColorPreviewIsDisplayedByDefaultInsideColorPickerFragment() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
        onView(withId(R.id.fragmentColorPickerPicker_colorPreview)).check(matches(isDisplayed()))
    }

    @Test
    fun checkFragmentColorPickerPickerDoneButtonIsDisplayedByDefaultInsideColorPickerFragment() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
        onView(withId(R.id.fragmentColorPickerPicker_doneButton)).check(matches(isDisplayed()))
    }

    @Test
    fun checkFragmentColorPickerPickerRootLayoutIsDisplayedWhenPickerTabIsPressedInsideColorPickerFragment() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
        onView(withText(R.string.fragmentColorPicker_tab_color_picker_str)).perform(longClick())
        onView(withId(R.id.fragmentColorPickerPicker_rootLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkFragmentColorPickerPickerColorPickerViewIsDisplayedWhenPickerTabIsPressedInsideColorPickerFragment() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
        onView(withText(R.string.fragmentColorPicker_tab_color_picker_str)).perform(longClick())
        onView(withId(R.id.fragmentColorPickerPicker_colorPickerView)).check(matches(isDisplayed()))
    }

    @Test
    fun checkFragmentColorPickerPickerColorPreviewIsDisplayedWhenPickerTabIsPressedInsideColorPickerFragment() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
        onView(withText(R.string.fragmentColorPicker_tab_color_picker_str)).perform(longClick())
        onView(withId(R.id.fragmentColorPickerPicker_colorPreview)).check(matches(isDisplayed()))
    }

    @Test
    fun checkFragmentColorPickerPickerDoneButtonIsDisplayedWhenPickerTabIsPressedInsideColorPickerFragment() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
        onView(withText(R.string.fragmentColorPicker_tab_color_picker_str)).perform(longClick())
        onView(withId(R.id.fragmentColorPickerPicker_doneButton)).check(matches(isDisplayed()))
    }







    @Test
    fun checkFragmentRGBColorPickerRootLayoutIsDisplayedWhenRGBTabIsPressedInsideColorPickerFragment() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
        onView(withText(R.string.fragmentColorPicker_tab_rgb_str)).perform(longClick())
        onView(withId(R.id.fragmentRGBColorPicker_rootLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkFragmentRGBColorPickerRedProgressBarIsDisplayedWhenRGBTabIsPressedInsideColorPickerFragment() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
        onView(withText(R.string.fragmentColorPicker_tab_rgb_str)).perform(longClick())
        onView(withId(R.id.fragmentRGBColorPicker_redProgressBar)).check(matches(isDisplayed()))
    }

    @Test
    fun checkFragmentRGBColorPickerGreenProgressBarIsDisplayedWhenRGBTabIsPressedInsideColorPickerFragment() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
        onView(withText(R.string.fragmentColorPicker_tab_rgb_str)).perform(longClick())
        onView(withId(R.id.fragmentRGBColorPicker_greenProgressBar)).check(matches(isDisplayed()))
    }

    @Test
    fun checkFragmentRGBColorPickerBlueProgressBarIsDisplayedWhenRGBTabIsPressedInsideColorPickerFragment() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
        onView(withText(R.string.fragmentColorPicker_tab_rgb_str)).perform(longClick())
        onView(withId(R.id.fragmentRGBColorPicker_blueProgressBar)).check(matches(isDisplayed()))
    }

    @Test
    fun checkFragmentRGBColorPickerColorPreviewIsDisplayedWhenRGBTabIsPressedInsideColorPickerFragment() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
        onView(withText(R.string.fragmentColorPicker_tab_rgb_str)).perform(longClick())
        onView(withId(R.id.fragmentRGBColorPicker_colorPreview)).check(matches(isDisplayed()))
    }

    @Test
    fun checkFragmentRGBColorPickerDoneButtonIsDisplayedWhenRGBTabIsPressedInsideColorPickerFragment() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
        onView(withText(R.string.fragmentColorPicker_tab_rgb_str)).perform(longClick())
        onView(withId(R.id.fragmentRGBColorPicker_doneButton)).check(matches(isDisplayed()))
    }

    @Test
    fun checkFragmentRGBColorPickerValueRIsDisplayedWhenRGBTabIsPressedInsideColorPickerFragment() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
        onView(withText(R.string.fragmentColorPicker_tab_rgb_str)).perform(longClick())
        onView(withId(R.id.fragmentRGBColorPicker_valueR)).check(matches(isDisplayed()))
    }

    @Test
    fun checkFragmentRGBColorPickerValueGIsDisplayedWhenRGBTabIsPressedInsideColorPickerFragment() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
        onView(withText(R.string.fragmentColorPicker_tab_rgb_str)).perform(longClick())
        onView(withId(R.id.fragmentRGBColorPicker_valueG)).check(matches(isDisplayed()))
    }

    @Test
    fun checkFragmentRGBColorPickerValueBIsDisplayedWhenRGBTabIsPressedInsideColorPickerFragment() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(longClick())
        onView(withText(R.string.fragmentColorPicker_tab_rgb_str)).perform(longClick())
        onView(withId(R.id.fragmentRGBColorPicker_valueB)).check(matches(isDisplayed()))
    }
}
