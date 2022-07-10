package com.therealbluepandabear.pixapencil.tooltests.tests

import android.graphics.Color
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.algorithms.FloodFillAlgorithm
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.extensions.getPixel
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.tooltests.helper.ToolTestsHelper
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class EraseToolTests {
    private fun iterate(func: (Coordinates) -> Unit) {
        for (i_1 in 0 until pixelGridViewInstance.pixelGridViewBitmap.width) {
            for (i_2 in 0 until pixelGridViewInstance.pixelGridViewBitmap.height) {
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

            iterate { coordinates ->
                assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(coordinates) == Color.BLACK)
            }

            it.viewModel.currentTool = Tool.EraseTool

            iterate { coordinates ->
                it.onPixelTapped(coordinates)
                assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(coordinates) == Color.TRANSPARENT)
            }
        }
    }
}