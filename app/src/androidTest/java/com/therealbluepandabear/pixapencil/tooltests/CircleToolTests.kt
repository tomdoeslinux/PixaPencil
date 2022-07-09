package com.therealbluepandabear.pixapencil.tooltests

import android.graphics.Color
import android.view.View
import androidx.core.graphics.set
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.binding
import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.overrideSetPixel
import com.therealbluepandabear.pixapencil.activities.canvas.initPrimaryAlgorithmInfoParameter
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.algorithms.AlgorithmInfoParameter
import com.therealbluepandabear.pixapencil.algorithms.CircleAlgorithm
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.extensions.getPixel
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.BitmapAction
import com.therealbluepandabear.pixapencil.models.Coordinates
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class CircleToolTests {
    // Functions
    private fun prepare(activity: CanvasActivity) {
        activity.viewModel.currentTool = Tool.CircleTool
        activity.viewModel.bitmapActionData = mutableListOf()
        activity.viewModel.currentBitmapAction = BitmapAction(mutableListOf())
        activity.initPrimaryAlgorithmInfoParameter()
    }

    // Test
    @get:Rule
    val activityRule = ActivityScenarioRule(CanvasActivity::class.java)

    @Test
    fun ctt_1() {
        activityRule.scenario.onActivity {
            prepare(it)

            val circleAlgorithm = CircleAlgorithm(it.primaryAlgorithmInfoParameter)
            circleAlgorithm.compute(Coordinates(0, 0), Coordinates(2, 2))

            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(1, 0)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(0, 1)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(2, 1)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(1, 2)) == Color.BLACK)
        }
    }

    @Test
    fun ctt_2() {
        activityRule.scenario.onActivity {
            prepare(it)

            val circleAlgorithm = CircleAlgorithm(it.primaryAlgorithmInfoParameter)
            circleAlgorithm.compute(Coordinates(0, 0), Coordinates(3, 3))

            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(1, 0)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(2, 0)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(0, 1)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(0, 2)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(1, 3)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(2, 3)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(3, 1)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(3, 2)) == Color.BLACK)
        }
    }

    @Test
    fun ctt_3() {
        activityRule.scenario.onActivity {
            prepare(it)

            val circleAlgorithm = CircleAlgorithm(it.primaryAlgorithmInfoParameter)
            circleAlgorithm.compute(Coordinates(0, 0), Coordinates(4, 4))

            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(1, 0)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(2, 0)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(3, 0)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(0, 1)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(0, 2)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(0, 3)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(1, 4)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(2, 4)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(3, 4)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(4, 1)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(4, 2)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(4, 3)) == Color.BLACK)
        }
    }

    @Test
    fun ctt_4() {
        activityRule.scenario.onActivity {
            prepare(it)

            val circleAlgorithm = CircleAlgorithm(it.primaryAlgorithmInfoParameter)
            circleAlgorithm.compute(Coordinates(0, 0), Coordinates(5, 5))

            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(1, 0)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(2, 0)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(3, 0)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(4, 0)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(0, 1)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(0, 2)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(0, 3)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(0, 4)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(1, 5)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(2, 5)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(3, 5)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(4, 5)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(5, 1)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(5, 2)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(5, 3)) == Color.BLACK)
            assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(Coordinates(5, 4)) == Color.BLACK)
        }
    }
}