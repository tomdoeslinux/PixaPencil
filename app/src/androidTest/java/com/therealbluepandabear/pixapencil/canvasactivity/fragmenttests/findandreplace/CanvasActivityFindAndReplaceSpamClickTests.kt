package com.therealbluepandabear.pixapencil.canvasactivity.fragmenttests.findandreplace

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class CanvasActivityFindAndReplaceSpamClickTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(CanvasActivity::class.java)
    
    @Test
    fun click_HexTab_50Times() {
    }
}