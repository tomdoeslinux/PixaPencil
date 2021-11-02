package com.realtomjoney.pyxlmoose

import android.graphics.Color
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.realtomjoney.pyxlmoose.activity.canvas.CanvasActivity
import com.realtomjoney.pyxlmoose.activity.canvas.extendedOnPause
import com.realtomjoney.pyxlmoose.activity.main.MainActivity
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [29])

class ExampleUnitTest {
    @Test
    fun test_SquareFrameLayout() {
        ActivityScenario.launch(MainActivity::class.java).onActivity { activity ->
            val squareFrameLayout = SquareFrameLayout(activity)
            assertEquals(squareFrameLayout.width, squareFrameLayout.height)
        }
    }

    @Test
    fun test_ColorPickerDatabase() {
        ColourDatabase.addItem(Color.BLUE)
        assert(ColourDatabase.toList().last() == Color.BLUE)
    }

    @Test
    fun test_canDeleteColors() {
        val previousSize = ColourDatabase.toList().size

        val last = ColourDatabase.toList().last()
        ColourDatabase.removeItem(last)

        assert(ColourDatabase.toList().size == previousSize - 1)
    }

    @Test
    fun test_initPixels() {
        ActivityScenario.launch(CanvasActivity::class.java).onActivity { activity ->
            val pixelData = activity.initPixels()
            assert(pixelData.isNotEmpty())
        }
    }

    @Test
    fun test_userCanDeletePixelArtItems() {
        ActivityScenario.launch(CanvasActivity::class.java).onActivity { activity ->
            activity.extendedOnPause()

            PixelArtDatabase.removeItem(PixelArtDatabase.toList().last())

            assert(PixelArtDatabase.toList().isEmpty())
        }
    }

    @Test
    fun test_extendedOnPause() {
        ActivityScenario.launch(CanvasActivity::class.java).onActivity { activity ->
            activity.extendedOnPause()

            val pixelArtData = PixelArtDatabase.toList()

            assert(pixelArtData.last().title == StringValues.DEFAULT_PROJECT_NAME)

            PixelArtDatabase.removeItem(PixelArtDatabase.toList().last())
        }
    }
}