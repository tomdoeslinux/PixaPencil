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
import com.therealbluepandabear.pixapencil.algorithms.CircleAlgorithm
import com.therealbluepandabear.pixapencil.extensions.getPixel
import com.therealbluepandabear.pixapencil.models.Coordinate
import com.therealbluepandabear.pixapencil.tooltests.helper.ToolTestsHelper
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class CircleToolTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(CanvasActivity::class.java)

    @Test
    fun ctt_1() {
        activityRule.scenario.onActivity {
            ToolTestsHelper.prepare(it)

            val circleAlgorithm = CircleAlgorithm(it.primaryAlgorithmInfoParameter)
            circleAlgorithm.compute(Coordinate(0, 0), Coordinate(2, 2))

            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(1, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(2, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(1, 2)) == Color.BLACK)
        }
    }

    @Test
    fun ctt_2() {
        activityRule.scenario.onActivity {
            ToolTestsHelper.prepare(it)

            val circleAlgorithm = CircleAlgorithm(it.primaryAlgorithmInfoParameter)
            circleAlgorithm.compute(Coordinate(0, 0), Coordinate(3, 3))

            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(1, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(2, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(1, 3)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(2, 3)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(3, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(3, 2)) == Color.BLACK)
        }
    }

    @Test
    fun ctt_3() {
        activityRule.scenario.onActivity {
            ToolTestsHelper.prepare(it)

            val circleAlgorithm = CircleAlgorithm(it.primaryAlgorithmInfoParameter)
            circleAlgorithm.compute(Coordinate(0, 0), Coordinate(4, 4))

            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(1, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(2, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(3, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 3)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(1, 4)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(2, 4)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(3, 4)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(4, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(4, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(4, 3)) == Color.BLACK)
        }
    }

    @Test
    fun ctt_4() {
        activityRule.scenario.onActivity {
            ToolTestsHelper.prepare(it)

            val circleAlgorithm = CircleAlgorithm(it.primaryAlgorithmInfoParameter)
            circleAlgorithm.compute(Coordinate(0, 0), Coordinate(5, 5))

            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(1, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(2, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(3, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(4, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 3)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 4)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(1, 5)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(2, 5)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(3, 5)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(4, 5)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(5, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(5, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(5, 3)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(5, 4)) == Color.BLACK)
        }
    }

    @Test
    fun ctt_5() {
        activityRule.scenario.onActivity {
            ToolTestsHelper.prepare(it)

            val circleAlgorithm = CircleAlgorithm(it.primaryAlgorithmInfoParameter)
            circleAlgorithm.compute(Coordinate(0, 0), Coordinate(6, 6))

            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(2, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(3, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(4, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 3)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 4)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(6, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(6, 3)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(6, 4)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(2, 6)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(3, 6)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(4, 6)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(1, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(5, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(5, 5)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(1, 5)) == Color.BLACK)
        }
    }

    @Test
    fun ctt_6() {
        activityRule.scenario.onActivity {
            ToolTestsHelper.prepare(it)

            val circleAlgorithm = CircleAlgorithm(it.primaryAlgorithmInfoParameter)
            circleAlgorithm.compute(Coordinate(0, 0), Coordinate(49, 49))

            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(25, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(49, 24)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(25, 49)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(49, 25)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(24, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 24)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 25)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(24, 49)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(26, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(49, 23)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(26, 49)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(49, 26)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(23, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 23)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 26)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(23, 49)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(27, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(49, 22)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(27, 49)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(49, 27)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(22, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 22)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 27)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(22, 49)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(28, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(49, 21)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(28, 49)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(49, 28)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(21, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 21)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 28)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(21, 49)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(29, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(49, 20)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(29, 49)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(49, 29)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(20, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 20)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(0, 29)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(20, 49)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(30, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(48, 19)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(30, 48)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(48, 30)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(19, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(1, 19)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(1, 30)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(19, 48)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(31, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(48, 18)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(31, 48)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(48, 31)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(18, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(1, 18)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(1, 31)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(18, 48)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(32, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(48, 17)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(32, 48)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(48, 32)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(17, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(1, 17)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(1, 32)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(17, 48)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(33, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(48, 16)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(33, 48)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(48, 33)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(16, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(1, 16)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(1, 33)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(16, 48)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(34, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(47, 15)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(34, 47)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(47, 34)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(15, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(2, 15)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(2, 34)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(15, 47)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(35, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(47, 14)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(35, 47)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(47, 35)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(14, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(2, 14)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(2, 35)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(14, 47)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(36, 3)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(46, 13)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(36, 46)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(46, 36)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(13, 3)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(3, 13)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(3, 36)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(13, 46)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(37, 3)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(46, 12)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(37, 46)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(46, 37)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(12, 3)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(3, 12)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(3, 37)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(12, 46)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(38, 4)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(45, 11)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(38, 45)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(45, 38)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(11, 4)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(4, 11)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(4, 38)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(11, 45)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(39, 5)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(44, 10)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(39, 44)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(44, 39)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(10, 5)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(5, 10)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(5, 39)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(10, 44)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(40, 5)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(44, 9)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(40, 44)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(44, 40)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(9, 5)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(5, 9)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(5, 40)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(9, 44)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(41, 6)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(43, 8)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(41, 43)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(43, 41)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(8, 6)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(6, 8)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(6, 41)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(8, 43)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(42, 7)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(42, 7)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(42, 42)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(42, 42)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(7, 7)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(7, 7)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(7, 42)) == Color.BLACK)
            assert(it.binding.activityCanvasDrawingView.drawingViewBitmap.getPixel(Coordinate(7, 42)) == Color.BLACK)
        }
    }
}