package com.therealbluepandabear.pixapencil.tooltests.tests

import android.graphics.Color
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.algorithms.RectanglePreviewAlgorithm
import com.therealbluepandabear.pixapencil.extensions.getPixel
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.tooltests.helper.ToolTestsHelper
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class RectangleToolTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(CanvasActivity::class.java)

    @Test
    fun rtt_1() {
        activityRule.scenario.onActivity {
            ToolTestsHelper.prepare(it)

            val rectangleAlgorithm = RectanglePreviewAlgorithm(it.primaryAlgorithmInfoParameter)
            rectangleAlgorithm.compute(Coordinates(0, 0), Coordinates(8, 2))

            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(1, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(2, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(3, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(4, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(5, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(6, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(7, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(8, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(8, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(1, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(2, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(3, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(4, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(5, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(6, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(7, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(8, 2)) == Color.BLACK)
        }
    }

    @Test
    fun rtt_2() {
        activityRule.scenario.onActivity {
            ToolTestsHelper.prepare(it)

            val rectangleAlgorithm = RectanglePreviewAlgorithm(it.primaryAlgorithmInfoParameter)
            rectangleAlgorithm.compute(Coordinates(0, 0), Coordinates(7, 2))

            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(1, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(2, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(3, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(4, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(5, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(6, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(7, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(7, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(1, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(2, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(3, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(4, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(5, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(6, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(7, 2)) == Color.BLACK)
        }
    }

    @Test
    fun rtt_3() {
        activityRule.scenario.onActivity {
            ToolTestsHelper.prepare(it)

            val rectangleAlgorithm = RectanglePreviewAlgorithm(it.primaryAlgorithmInfoParameter)
            rectangleAlgorithm.compute(Coordinates(0, 0), Coordinates(6, 2))

            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(1, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(2, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(3, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(4, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(5, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(6, 0)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(6, 1)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(0, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(1, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(2, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(3, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(4, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(5, 2)) == Color.BLACK)
            assert(it.binding.activityCanvasPixelGridView.pixelGridViewBitmap.getPixel(Coordinates(6, 2)) == Color.BLACK)
        }
    }
}