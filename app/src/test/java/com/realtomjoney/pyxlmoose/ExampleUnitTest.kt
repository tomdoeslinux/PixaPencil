package com.realtomjoney.pyxlmoose

import android.widget.LinearLayout
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
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
}