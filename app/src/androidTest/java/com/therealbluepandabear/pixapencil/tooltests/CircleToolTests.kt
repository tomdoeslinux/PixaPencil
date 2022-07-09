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
}