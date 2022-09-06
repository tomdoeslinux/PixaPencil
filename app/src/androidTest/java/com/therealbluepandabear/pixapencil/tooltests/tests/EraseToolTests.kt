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
import com.therealbluepandabear.pixapencil.algorithms.FloodFillAlgorithm
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.extensions.getPixel
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.tooltests.helper.ToolTestsHelper
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class EraseToolTests {
    private fun iterate(activity: CanvasActivity, func: (Coordinates) -> Unit) {
        for (i_1 in 0 until activity.binding.activityCanvasPixelGridView.pixelGridViewBitmap.width) {
            for (i_2 in 0 until activity.binding.activityCanvasPixelGridView.pixelGridViewBitmap.height) {
                func.invoke(Coordinates(i_1, i_2))
            }
        }
    }

    @get:Rule
    val activityRule = ActivityScenarioRule(CanvasActivity::class.java)

    @Test
    fun ett_1() {
        activityRule.scenario.onActivity {
            ToolTestsHelper.prepare(it)

            val floodFillAlgorithm = FloodFillAlgorithm(it.primaryAlgorithmInfoParameter)
            floodFillAlgorithm.compute(Coordinates(0,0))

            iterate(it) { coordinates ->
                assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(coordinates) == Color.BLACK)
            }

            it.viewModel.currentTool = Tool.EraseTool

            iterate(it) { coordinates ->
                it.onPixelTapped(coordinates)
                assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(coordinates) == Color.TRANSPARENT)
            }
        }
    }
}