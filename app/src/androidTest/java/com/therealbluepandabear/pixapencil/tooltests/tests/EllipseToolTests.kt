package com.therealbluepandabear.pixapencil.tooltests.tests

import android.graphics.Color
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.algorithms.CircleAlgorithm
import com.therealbluepandabear.pixapencil.algorithms.EllipseAlgorithm
import com.therealbluepandabear.pixapencil.extensions.getPixel
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.Coordinates
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
            ellipseAlgorithm.compute(Coordinates(0, 0), Coordinates(9, 4))

            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(2, 0)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(3, 0)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(4, 0)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(5, 0)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(6, 0)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(7, 0)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(2, 4)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(3, 4)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(4, 4)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(5, 4)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(6, 4)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(7, 4)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(1, 1)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(0, 2)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(1, 3)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(8, 1)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(9, 2)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(8, 3)) == Color.BLACK)
        }
    }

    @Test
    fun ett_2() {
        activityRule.scenario.onActivity {
            ToolTestsHelper.prepare(it)

            val ellipseAlgorithm = EllipseAlgorithm(it.primaryAlgorithmInfoParameter)
            ellipseAlgorithm.compute(Coordinates(0, 0), Coordinates(4, 9))

            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(2, 0)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(1, 1)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(3, 1)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(1, 8)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(2, 9)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(3, 8)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(0, 2)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(0, 3)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(0, 4)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(0, 5)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(0, 6)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(0, 7)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(4, 2)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(4, 3)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(4, 4)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(4, 5)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(4, 6)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(4, 7)) == Color.BLACK)
        }
    }
}