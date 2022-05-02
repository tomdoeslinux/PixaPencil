package com.therealbluepandabear.pixapencil.canvasactivity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class CanvasActivityTabTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(CanvasActivity::class.java)

    @Test
    fun checkFragmentToolsRootLayoutIsDisplayedByDefault() {
        onView(withId(R.id.fragmentTools_rootLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkFragmentFiltersRootLayoutIsDisplayedWhenFiltersTabClicked() {
        onView(ViewMatchers.withText(R.string.activityCanvas_tab_filters_str)).perform(click())
        onView(withId(R.id.fragmentFilters_rootLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkFragmentColorPalettesRootLayoutIsDisplayedWhenFiltersTabClicked() {
        onView(ViewMatchers.withText(R.string.activityCanvas_tab_color_palettes_str)).perform(click())
        onView(withId(R.id.fragmentColorPalettes_rootLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkFragmentBrushesRootLayoutIsDisplayedWhenFiltersTabClicked() {
        onView(ViewMatchers.withText(R.string.activityCanvas_tab_brushes_str)).perform(click())
        onView(withId(R.id.fragmentBrushes_rootLayout)).check(matches(isDisplayed()))
    }
}