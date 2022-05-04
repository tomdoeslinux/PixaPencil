package com.therealbluepandabear.pixapencil.canvasactivity.fragmenttests.findandreplace

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test completion summary for `CanvasActivityFindAndReplaceFragmentTests`:
 *
 * **Last completion of tests in this package file:**
 *
 * - 2022-05-02 20:59 (1/1 passed) on API 32
 *
 * **Last completion of tests in this package file for API 32:**
 * - 2022-05-02 20:59 (1/1 passed)
 */

@RunWith(AndroidJUnit4::class)
@LargeTest
class CanvasActivityFindAndReplaceFragmentTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(CanvasActivity::class.java)

    @Before
    fun setup() {
        onView(withId(R.id.activityCanvas_outerCanvasFragmentHost)).perform(click())
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().context)
        onView(withText(R.string.activityCanvasTopAppMenu_find_and_replace_str)).perform(click())
    }

    @Test
    fun checkRootLayout_IsDisplayed() {
        onView(withId(R.id.fragmentFindAndReplace_rootLayout)).check(matches(isDisplayed()))
    }
}