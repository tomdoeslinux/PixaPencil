package com.therealbluepandabear.pixapencil.canvasactivity.fragmenttests.colorpicker.colorpickerfragment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.longClick
import androidx.test.espresso.matcher.ViewMatchers.withText
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
class CanvasActivityFragmentColorPickerTabSpamClickTests {
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

    // Picker tab

    @Test
    fun click_PickerTab() {
        pressPickerTab()
    }

    @Test
    fun click_PickerTab_25Times() {
        for (i in 0..25) {
            pressPickerTab()
        }
    }

    @Test
    fun click_PickerTab_50Times() {
        for (i in 0..50) {
            pressPickerTab()
        }
    }

    @Test
    fun click_PickerTab_100Times() {
        for (i in 0..100) {
            pressPickerTab()
        }
    }

    // Hex tab

    @Test
    fun click_HexTab() {
        pressHexTab()
    }

    @Test
    fun click_HexTab_25Times() {
        for (i in 0..25) {
            pressHexTab()
        }
    }

    @Test
    fun click_HexTab_50Times() {
        for (i in 0..50) {
            pressHexTab()
        }
    }

    @Test
    fun click_HexTab_100Times() {
        for (i in 0..100) {
            pressHexTab()
        }
    }

    // RGB tab

    @Test
    fun click_RGBTab() {
        pressRGBTab()
    }

    @Test
    fun click_RGBTab_25Times() {
        for (i in 0..25) {
            pressRGBTab()
        }
    }

    @Test
    fun click_RGBTab_50Times() {
        for (i in 0..50) {
            pressRGBTab()
        }
    }

    @Test
    fun click_RGBTab_100Times() {
        for (i in 0..100) {
            pressRGBTab()
        }
    }
}