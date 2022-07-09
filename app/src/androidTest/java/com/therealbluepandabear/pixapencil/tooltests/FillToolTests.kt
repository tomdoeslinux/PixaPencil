package com.therealbluepandabear.pixapencil.tooltests

import android.graphics.Color
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.overrideSetPixel
import com.therealbluepandabear.pixapencil.algorithms.CircleAlgorithm
import com.therealbluepandabear.pixapencil.algorithms.FloodFillAlgorithm
import com.therealbluepandabear.pixapencil.extensions.getPixel
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.tooltests.helper.ToolTestsHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class FillToolTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(CanvasActivity::class.java)

    @Test
    fun ftt_1() {
        activityRule.scenario.onActivity {
            ToolTestsHelper.prepare(it)

            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinates(0,0), Color.YELLOW)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinates(0,1), Color.YELLOW)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinates(1,2), Color.YELLOW)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinates(2,3), Color.YELLOW)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinates(3,3), Color.YELLOW)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinates(4,3), Color.YELLOW)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinates(5,4), Color.YELLOW)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinates(6,5), Color.YELLOW)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinates(7,4), Color.YELLOW)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinates(7,3), Color.YELLOW)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinates(7,2), Color.YELLOW)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinates(6,1), Color.YELLOW)
            it.canvasCommandsHelperInstance.overrideSetPixel(Coordinates(5,0), Color.YELLOW)

            val floodFillAlgorithm = FloodFillAlgorithm(it.primaryAlgorithmInfoParameter)
            floodFillAlgorithm.compute(Coordinates(1, 1))

            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(1, 0)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(2, 0)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(3, 0)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(4, 0)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(1, 1)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(2, 1)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(3, 1)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(4, 1)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(5, 1)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(2, 2)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(3, 2)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(4, 2)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(5, 2)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(6, 2)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(5, 3)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(6, 3)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(6, 4)) == Color.BLACK)
        }
    }
}