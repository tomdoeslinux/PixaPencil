package com.therealbluepandabear.pixapencil.canvasactivity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.*
import org.hamcrest.core.IsNot.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test completion summary for `CanvasActivityTabTests`:
 *
 * **Last completion of tests in this package file:**
 *
 * - 2022-05-02 21:00 (14/14 passed) on API 32
 *
 * **Last completion of tests in this package file for API 32:**
 * - 2022-05-02 21:00 (14/14 passed)
 */

@RunWith(AndroidJUnit4::class)
@LargeTest
class CanvasActivityTabTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(CanvasActivity::class.java)

    private fun notDisplayedSetup() {
        onView(withText(R.string.activityCanvas_tab_tools_str)).perform(click())
        onView(withText(R.string.activityCanvas_tab_color_palettes_str)).perform(click())
        onView(withText(R.string.activityCanvas_tab_filters_str)).perform(click())
        onView(withText(R.string.activityCanvas_tab_brushes_str)).perform(click())
    }

    @Test
    fun checkFragmentToolsRootLayoutIsDisplayedByDefault() {
        onView(withId(R.id.fragmentTools_rootLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkFragmentFiltersRootLayoutIsDisplayedWhenFiltersTabClicked() {
        onView(withText(R.string.activityCanvas_tab_filters_str)).perform(click())
        onView(withId(R.id.fragmentFilters_rootLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkFragmentColorPalettesRootLayoutIsDisplayedWhenColorPalettesTabClicked() {
        onView(withText(R.string.activityCanvas_tab_color_palettes_str)).perform(click())
        onView(withId(R.id.fragmentColorPalettes_rootLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkFragmentBrushesRootLayoutIsDisplayedWhenBrushesTabClicked() {
        onView(withText(R.string.activityCanvas_tab_brushes_str)).perform(click())
        onView(withId(R.id.fragmentBrushes_rootLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkFragmentToolsRootLayoutIsDisplayedWhenToolsTabClicked() {
        onView(withText(R.string.activityCanvas_tab_tools_str)).perform(click())
        onView(withId(R.id.fragmentTools_rootLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkOtherFragmentsAreNotDisplayedWhenToolsTabClicked() {
        notDisplayedSetup()
        onView(withText(R.string.activityCanvas_tab_tools_str)).perform(click())

        onView(withId(brushesFragmentInstance!!.requireView().id)).check(matches(not(isDisplayed())))
        onView(withId(filtersFragmentInstance!!.requireView().id)).check(matches(not(isDisplayed())))
        onView(withId(colorPalettesFragmentInstance!!.requireView().id)).check(matches(not(isDisplayed())))
    }

    @Test
    fun checkOtherFragmentsAreNotDisplayedWhenFiltersTabClicked() {
        notDisplayedSetup()
        onView(withText(R.string.activityCanvas_tab_filters_str)).perform(click())

        onView(withId(brushesFragmentInstance!!.requireView().id)).check(matches(not(isDisplayed())))
        onView(withId(toolsFragmentInstance!!.requireView().id)).check(matches(not(isDisplayed())))
        onView(withId(colorPalettesFragmentInstance!!.requireView().id)).check(matches(not(isDisplayed())))
    }

    @Test
    fun checkOtherFragmentsAreNotDisplayedWhenPalettesTabClicked() {
        notDisplayedSetup()
        onView(withText(R.string.activityCanvas_tab_color_palettes_str)).perform(click())

        onView(withId(brushesFragmentInstance!!.requireView().id)).check(matches(not(isDisplayed())))
        onView(withId(toolsFragmentInstance!!.requireView().id)).check(matches(not(isDisplayed())))
        onView(withId(filtersFragmentInstance!!.requireView().id)).check(matches(not(isDisplayed())))
    }

    @Test
    fun checkOtherFragmentsAreNotDisplayedWhenBrushesTabClicked() {
        notDisplayedSetup()

        onView(withId(filtersFragmentInstance!!.requireView().id)).check(matches(not(isDisplayed())))
        onView(withId(toolsFragmentInstance!!.requireView().id)).check(matches(not(isDisplayed())))
        onView(withId(colorPalettesFragmentInstance!!.requireView().id)).check(matches(not(isDisplayed())))
    }

    @Test
    fun checkToolsFragmentInstanceIsDisplayedByDefault() {
        onView(withId(toolsFragmentInstance!!.requireView().id)).check(matches(isDisplayed()))
    }

    @Test
    fun checkFiltersFragmentInstanceIsDisplayedWhenFiltersTabClicked() {
        onView(withText(R.string.activityCanvas_tab_filters_str)).perform(click())
        onView(withId(filtersFragmentInstance!!.requireView().id)).check(matches(isDisplayed()))
    }

    @Test
    fun checkColorPalettesFragmentInstanceIsDisplayedWhenColorPalettesTabClicked() {
        onView(withText(R.string.activityCanvas_tab_color_palettes_str)).perform(click())
        onView(withId(colorPalettesFragmentInstance!!.requireView().id)).check(matches(isDisplayed()))
    }

    @Test
    fun checkBrushesFragmentInstanceIsDisplayedWhenBrushesTabClicked() {
        onView(withText(R.string.activityCanvas_tab_brushes_str)).perform(click())
        onView(withId(brushesFragmentInstance!!.requireView().id)).check(matches(isDisplayed()))
    }

    @Test
    fun checkToolsFragmentInstanceIsDisplayedWhenToolsTabClicked() {
        onView(withText(R.string.activityCanvas_tab_tools_str)).perform(click())
        onView(withId(toolsFragmentInstance!!.requireView().id)).check(matches(isDisplayed()))
    }
}