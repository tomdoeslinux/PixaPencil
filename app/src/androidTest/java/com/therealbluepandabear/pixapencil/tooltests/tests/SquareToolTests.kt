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
import com.therealbluepandabear.pixapencil.algorithms.SquarePreviewAlgorithm
import com.therealbluepandabear.pixapencil.extensions.getPixel
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.tooltests.helper.ToolTestsHelper
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class SquareToolTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(CanvasActivity::class.java)

    @Test
    fun stt_1() {
        activityRule.scenario.onActivity {
            ToolTestsHelper.prepare(it)

            val squareAlgorithm = SquarePreviewAlgorithm(it.primaryAlgorithmInfoParameter)
            squareAlgorithm.compute(Coordinates(0, 0), Coordinates(2, 2))

            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(1, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(2, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(2, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(2, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(1, 2)) == Color.BLACK)
        }
    }

    @Test
    fun stt_2() {
        activityRule.scenario.onActivity {
            ToolTestsHelper.prepare(it)

            val squareAlgorithm = SquarePreviewAlgorithm(it.primaryAlgorithmInfoParameter)
            squareAlgorithm.compute(Coordinates(0, 0), Coordinates(3, 3))

            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(1, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(2, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(3, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 3)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(1, 3)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(2, 3)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(3, 3)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(3, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(3, 2)) == Color.BLACK)
        }
    }

    @Test
    fun stt_3() {
        activityRule.scenario.onActivity {
            ToolTestsHelper.prepare(it)

            val squareAlgorithm = SquarePreviewAlgorithm(it.primaryAlgorithmInfoParameter)
            squareAlgorithm.compute(Coordinates(0, 0), Coordinates(4, 4))

            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(1, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(2, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(3, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(4, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 3)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 4)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(1, 4)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(2, 4)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(3, 4)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(4, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(4, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(4, 3)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(4, 4)) == Color.BLACK)
        }
    }

    @Test
    fun stt_4() {
        activityRule.scenario.onActivity {
            ToolTestsHelper.prepare(it)

            val squareAlgorithm = SquarePreviewAlgorithm(it.primaryAlgorithmInfoParameter)
            squareAlgorithm.compute(Coordinates(0, 0), Coordinates(5, 5))

            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(1, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(2, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(3, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(4, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(5, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 3)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 4)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(5, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(5, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(5, 3)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(5, 4)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 5)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(1, 5)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(2, 5)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(3, 5)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(4, 5)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(5, 5)) == Color.BLACK)
        }
    }

    @Test
    fun stt_5() {
        activityRule.scenario.onActivity {
            ToolTestsHelper.prepare(it)

            val squareAlgorithm = SquarePreviewAlgorithm(it.primaryAlgorithmInfoParameter)
            squareAlgorithm.compute(Coordinates(0, 0), Coordinates(6, 6))

            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(1, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(2, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(3, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(4, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(5, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(6, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 6)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(1, 6)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(2, 6)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(3, 6)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(4, 6)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(5, 6)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(6, 6)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 3)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 4)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 5)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(6, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(6, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(6, 3)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(6, 4)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(6, 5)) == Color.BLACK)
        }
    }
}