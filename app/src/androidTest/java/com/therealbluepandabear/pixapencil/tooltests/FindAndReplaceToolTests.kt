package com.therealbluepandabear.pixapencil.tooltests

import android.graphics.Color
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.overrideSetPixel
import com.therealbluepandabear.pixapencil.algorithms.FloodFillAlgorithm
import com.therealbluepandabear.pixapencil.extensions.getPixel
import com.therealbluepandabear.pixapencil.extensions.setPixel
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.tooltests.helper.ToolTestsHelper
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.random.Random

@RunWith(AndroidJUnit4::class)
@LargeTest
class FindAndReplaceToolTests {
    private fun setRandomPixels(activity: CanvasActivity, iterations: Int, color: Int = Color.BLACK): List<Coordinates> {
        val randomCoordinates: MutableList<Coordinates> = mutableListOf()

        for (i in 0..iterations) {
            val randomCoordinate = Coordinates(Random.nextInt(0, activity.width), Random.nextInt(0, activity.height))
            randomCoordinates.add(randomCoordinate)

            pixelGridViewInstance.pixelGridViewBitmap.setPixel(randomCoordinate, color)
        }

        return randomCoordinates
    }

    @get:Rule
    val activityRule = ActivityScenarioRule(CanvasActivity::class.java)

    @Test
    fun rnd_fartt_1() {
        activityRule.scenario.onActivity {
            ToolTestsHelper.prepare(it)

            val iterations = 200
            val randomCoordinates: List<Coordinates> = setRandomPixels(it, iterations)

            // For all tests after this: this is the specific onDoneButtonPressed function for the 'Done' button in the 'Find and Replace' fragment.
            it.onDoneButtonPressed(Color.BLACK, Color.YELLOW)

            for (coordinate in randomCoordinates) {
                assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(coordinate) == Color.YELLOW)
            }
        }
    }

    @Test
    fun rnd_fartt_2() {
        activityRule.scenario.onActivity {
            activityRule.scenario.onActivity {
                ToolTestsHelper.prepare(it)

                val iterations = 2000
                val randomCoordinates: List<Coordinates> = setRandomPixels(it, iterations)

                it.onDoneButtonPressed(Color.BLACK, Color.CYAN)

                for (coordinate in randomCoordinates) {
                    assert(pixelGridViewInstance.pixelGridViewBitmap.getPixel(coordinate) == Color.CYAN)
                }
            }
        }
    }
}