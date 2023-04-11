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
import com.therealbluepandabear.pixapencil.enums.SymmetryMode
import com.therealbluepandabear.pixapencil.extensions.getPixel
import com.therealbluepandabear.pixapencil.models.Coordinate
import com.therealbluepandabear.pixapencil.tooltests.helper.ToolTestsHelper
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class SymmetryTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(CanvasActivity::class.java)

    @Test
    fun v_stt_1() {
        activityRule.scenario.onActivity {
            ToolTestsHelper.prepare(it)
            it.viewModel.currentSymmetryMode = SymmetryMode.Vertical

            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(2, 3), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(3, 4), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(4, 4), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(5, 5), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(6, 6), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(6, 7), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(6, 8), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(6, 9), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(6, 10), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(6, 11), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(6, 12), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(6, 13), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(6, 14), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(5, 15), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(4, 16), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(5, 17), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(6, 18), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(7, 18), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(8, 18), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(9, 19), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(10, 20), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(10, 20), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(10, 20), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(10, 21), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(10, 22), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(9, 23), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(8, 24), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(7, 25), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(7, 26), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(7, 27), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(7, 28), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(6, 29), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(5, 30), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(5, 31), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(5, 32), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(6, 33), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(6, 33), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(6, 33), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(7, 34), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(8, 35), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(8, 36), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(8, 36), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(8, 36), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(8, 37), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(8, 38), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(8, 39), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(8, 40), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(7, 41), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(6, 42), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(5, 44), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(5, 43), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(5, 45), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(5, 46), Color.BLACK)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinate(5, 47), Color.BLACK)

            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(47, 3)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(46, 4)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(45, 4)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(44, 5)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(43, 6)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(43, 7)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(43, 8)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(43, 9)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(43, 10)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(43, 11)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(43, 12)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(43, 13)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(43, 14)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(44, 15)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(45, 16)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(44, 17)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(43, 18)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(42, 18)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(41, 18)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(40, 19)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(39, 20)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(39, 20)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(39, 20)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(39, 21)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(39, 22)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(40, 23)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(41, 24)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(42, 25)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(42, 26)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(42, 27)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(42, 28)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(43, 29)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(44, 30)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(44, 31)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(44, 32)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(43, 33)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(43, 33)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(43, 33)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(42, 34)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(41, 35)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(41, 36)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(41, 36)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(41, 36)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(41, 37)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(41, 38)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(41, 39)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(41, 40)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(42, 41)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(43, 42)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(44, 44)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(44, 43)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(44, 45)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(44, 46)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(44, 47)) == Color.BLACK)
        }
    }
}