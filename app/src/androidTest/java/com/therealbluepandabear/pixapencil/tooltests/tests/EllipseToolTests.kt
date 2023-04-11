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
import com.therealbluepandabear.pixapencil.algorithms.EllipseAlgorithm
import com.therealbluepandabear.pixapencil.extensions.getPixel
import com.therealbluepandabear.pixapencil.models.Coordinate
import com.therealbluepandabear.pixapencil.tooltests.helper.ToolTestsHelper
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class EllipseToolTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(CanvasActivity::class.java)

    @Test
    fun ett_1() {
        activityRule.scenario.onActivity {
            ToolTestsHelper.prepare(it)

            val ellipseAlgorithm = EllipseAlgorithm(it.primaryAlgorithmInfoParameter)
            ellipseAlgorithm.compute(Coordinate(0, 0), Coordinate(9, 4))

            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(2, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(3, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(4, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(5, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(6, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(7, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(2, 4)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(3, 4)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(4, 4)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(5, 4)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(6, 4)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(7, 4)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(1, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(1, 3)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(8, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(9, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(8, 3)) == Color.BLACK)
        }
    }

    @Test
    fun ett_2() {
        activityRule.scenario.onActivity {
            ToolTestsHelper.prepare(it)

            val ellipseAlgorithm = EllipseAlgorithm(it.primaryAlgorithmInfoParameter)
            ellipseAlgorithm.compute(Coordinate(0, 0), Coordinate(4, 9))

            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(2, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(1, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(3, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(1, 8)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(2, 9)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(3, 8)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 3)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 4)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 5)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 6)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 7)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(4, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(4, 3)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(4, 4)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(4, 5)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(4, 6)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(4, 7)) == Color.BLACK)
        }
    }

    @Test
    fun ett_3() {
        activityRule.scenario.onActivity {
            ToolTestsHelper.prepare(it)

            val ellipseAlgorithm = EllipseAlgorithm(it.primaryAlgorithmInfoParameter)
            ellipseAlgorithm.compute(Coordinate(0, 0), Coordinate(9, 7))

            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(2, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(3, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(4, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(5, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(6, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(7, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(2, 7)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(3, 7)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(4, 7)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(5, 7)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(6, 7)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(7, 7)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(1, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(8, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(1, 6)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(8, 6)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 3)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 4)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 5)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(9, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(9, 3)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(9, 4)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(9, 5)) == Color.BLACK)
        }
    }

    @Test
    fun ett_4() {
        activityRule.scenario.onActivity {
            ToolTestsHelper.prepare(it)

            val ellipseAlgorithm = EllipseAlgorithm(it.primaryAlgorithmInfoParameter)
            ellipseAlgorithm.compute(Coordinate(0, 0), Coordinate(29, 24))

            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(15, 24)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(15, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(14, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(14, 24)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(16, 24)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(16, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(13, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(13, 24)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(17, 24)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(17, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(12, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(12, 24)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(18, 24)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(18, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(11, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(11, 24)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(19, 23)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(19, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(10, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(10, 23)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(20, 23)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(20, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(9, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(9, 23)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(21, 23)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(21, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(8, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(8, 23)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(22, 22)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(22, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(7, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(7, 22)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(23, 22)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(23, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(6, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(6, 22)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(24, 21)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(24, 3)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(5, 3)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(5, 21)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(25, 20)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(25, 4)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(4, 4)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(4, 20)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(26, 19)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(26, 5)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(3, 5)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(3, 19)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(27, 18)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(27, 6)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(2, 6)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(2, 18)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(28, 17)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(28, 7)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(1, 7)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(1, 17)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(28, 16)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(28, 8)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(1, 8)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(1, 16)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(29, 15)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(29, 9)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 9)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 15)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(29, 14)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(29, 10)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 10)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 14)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(29, 13)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(29, 11)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 11)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 13)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(29, 12)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(29, 12)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 12)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 12)) == Color.BLACK)
        }
    }

    @Test
    fun ett_5() {
        activityRule.scenario.onActivity {
            ToolTestsHelper.prepare(it)

            val ellipseAlgorithm = EllipseAlgorithm(it.primaryAlgorithmInfoParameter)
            ellipseAlgorithm.compute(Coordinate(0, 0), Coordinate(29, 9))

            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(15, 9)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(15, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(14, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(14, 9)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(16, 9)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(16, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(13, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(13, 9)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(17, 9)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(17, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(12, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(12, 9)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(18, 9)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(18, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(11, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(11, 9)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(19, 9)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(19, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(10, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(10, 9)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(20, 9)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(20, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(9, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(9, 9)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(21, 9)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(21, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(8, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(8, 9)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(22, 8)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(22, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(7, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(7, 8)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(23, 8)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(23, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(6, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(6, 8)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(24, 8)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(24, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(5, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(5, 8)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(25, 8)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(25, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(4, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(4, 8)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(26, 7)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(26, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(3, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(3, 7)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(27, 7)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(27, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(2, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(2, 7)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(28, 6)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(28, 3)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(1, 3)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(1, 6)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(29, 5)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(29, 4)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 4)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 5)) == Color.BLACK)
        }
    }
}