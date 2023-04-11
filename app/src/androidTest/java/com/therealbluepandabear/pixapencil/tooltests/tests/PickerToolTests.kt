/*
 * PixaPencil
 * Copyright 2022  therealbluepandabear
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.therealbluepandabear.pixapencil.tooltests.tests

import android.graphics.Color
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.overrideSetPixel
import com.therealbluepandabear.pixapencil.activities.canvas.getSelectedColor
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.models.Coordinate
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

            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(0,0), Color.BLUE)
            it.onPixelTapped(Coordinate(0, 0))

            assert(it.binding.activityCanvasColorSwitcherView.getPrimaryColor() == Color.BLUE)
            assert(it.getSelectedColor() == Color.BLUE)
            assert(it.viewModel.primaryColor == Color.BLUE)
        }
    }

    @Test
    fun ptt_2() {
        activityRule.scenario.onActivity {
            ToolTestsHelper.prepare(it)

            it.viewModel.currentTool = Tool.ColorPickerTool
            it.binding.activityCanvasColorSwitcherView.setIsPrimarySelected(false)

            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(0,0), Color.GREEN)
            it.onPixelTapped(Coordinate(0, 0))

            assert(it.binding.activityCanvasColorSwitcherView.getSecondaryColor() == Color.GREEN)
            assert(it.getSelectedColor() == Color.GREEN)
            assert(it.viewModel.secondaryColor == Color.GREEN)
        }
    }
}