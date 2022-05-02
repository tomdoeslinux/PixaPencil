package com.therealbluepandabear.pixapencil.canvasactivity

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

@RunWith(AndroidJUnit4::class)
@LargeTest
class CanvasActivityFindAndReplaceFragmentTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(CanvasActivity::class.java)

    @Before
    fun setup() {
        onView(withId(R.id.activityCanvas_outerCanvasFragmentHost)).perform(click())
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().context)
    }

    @Test
    fun checkFindAndReplaceFragmentRootLayoutIsDisplayedWhenFindAndReplaceButtonMenuItemIsClicked() {
        onView(withText(R.string.activityCanvasTopAppMenu_find_and_replace_str)).perform(click())
        onView(withId(R.id.fragmentFindAndReplace_rootLayout)).check(matches(isDisplayed()))
    }
}