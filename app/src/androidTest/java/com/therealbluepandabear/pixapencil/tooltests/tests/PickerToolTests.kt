package com.therealbluepandabear.pixapencil.tooltests.tests

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.binding
import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.overrideSetPixel
import com.therealbluepandabear.pixapencil.activities.canvas.getSelectedColor
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.tooltests.helper.ToolTestsHelper
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class PickerToolTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(CanvasActivity::class.java)

    @Test
    fun ptt_1() {
        activityRule.scenario.onActivity {
            ToolTestsHelper.prepare(it)

            it.viewModel.currentTool = Tool.ColorPickerTool

            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinates(0,0), Color.BLUE)
            it.onPixelTapped(Coordinates(0, 0))

            assert((binding.activityCanvasColorPrimaryView.background as ColorDrawable).color == Color.BLUE)
            assert(it.getSelectedColor() == Color.BLUE)
        }
    }

    @Test
    fun ptt_2() {
        activityRule.scenario.onActivity {
            ToolTestsHelper.prepare(it)

            it.viewModel.currentTool = Tool.ColorPickerTool
            binding.activityCanvasColorSecondaryView.performClick()

            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinates(0,0), Color.GREEN)
            it.onPixelTapped(Coordinates(0, 0))

            assert((binding.activityCanvasColorSecondaryView.background as ColorDrawable).color == Color.GREEN)
            assert(it.getSelectedColor() == Color.GREEN)
        }
    }
}