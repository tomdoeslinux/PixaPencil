package com.therealbluepandabear.pixapencil.canvasactivity.fragmenttests.colorpicker

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
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
class CanvasActivityFragmentRGBColorPickerViewDisplayedTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(CanvasActivity::class.java)

    @Before
    fun setup() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).perform(ViewActions.longClick())
        onView(ViewMatchers.withText(R.string.fragmentColorPicker_tab_rgb_str)).perform(ViewActions.longClick())
    }

    @Test
    fun checkColorPickerRootLayout_IsDisplayed() {
        onView(withId(R.id.fragmentRGBColorPicker_rootLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkColorPickerRedProgressBar_IsDisplayed() {
        onView(withId(R.id.fragmentRGBColorPicker_redProgressBar)).check(matches(isDisplayed()))
    }

    @Test
    fun checkColorPickerGreenProgressBar_IsDisplayed() {
        onView(withId(R.id.fragmentRGBColorPicker_greenProgressBar)).check(matches(isDisplayed()))
    }

    @Test
    fun checkColorPickerBlueProgressBar_IsDisplayed() {
        onView(withId(R.id.fragmentRGBColorPicker_blueProgressBar)).check(matches(isDisplayed()))
    }

    @Test
    fun checkColorPickerColorPreview_IsDisplayed() {
        onView(withId(R.id.fragmentRGBColorPicker_colorPreview)).check(matches(isDisplayed()))
    }

    @Test
    fun checkColorPickerValueR_IsDisplayed() {
        onView(withId(R.id.fragmentRGBColorPicker_valueR)).check(matches(isDisplayed()))
    }

    @Test
    fun checkColorPickerValueG_IsDisplayed() {
        onView(withId(R.id.fragmentRGBColorPicker_valueG)).check(matches(isDisplayed()))
    }

    @Test
    fun checkColorPickerValueB_IsDisplayed() {
        onView(withId(R.id.fragmentRGBColorPicker_valueB)).check(matches(isDisplayed()))
    }
}