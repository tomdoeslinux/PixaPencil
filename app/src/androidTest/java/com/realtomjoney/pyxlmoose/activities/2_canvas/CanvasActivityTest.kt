package com.realtomjoney.pyxlmoose.activities.`2_canvas`

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.realtomjoney.pyxlmoose.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.realtomjoney.pyxlmoose.activities.canvas.CanvasActivity
import com.realtomjoney.pyxlmoose.database.ColourDatabase
import com.realtomjoney.pyxlmoose.viewholders.RecyclerViewHolder


@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class CanvasActivityTest {
    @get:Rule
    var activityTestRule = ActivityTestRule(CanvasActivity::class.java)

    private fun goToFindAndReplaceFragment() {

    }

    private fun goToColorPickerFragment() {

    }


    @Test fun uitest_doneButtonFAB_isDisplayed() {
        onView(withId(R.id.doneButton)).check(matches(isDisplayed()))
    }

    @Test fun uitest_colourPickerRecyclerView_isDisplayed() {
        onView(withId(R.id.colourPickerRecyclerView)).check(matches(isDisplayed()))
    }

    @Test fun uitest_titleTextView_isDisplayed() {
        onView(withId(R.id.titleTextView)).check(matches(isDisplayed()))
    }

    @Test fun uitest_colorSwapButton_isDisplayed() {
        onView(withId(R.id.colorSwapButton)).check(matches(isDisplayed()))
    }

    @Test fun uitest_colourPrimarySelected_isDisplayed() {
        onView(withId(R.id.colourPrimarySelected)).check(matches(isDisplayed()))
    }

    @Test fun uitest_colourSecondarySelected_isDisplayed() {
        onView(withId(R.id.colourSecondarySelected)).check(matches(isDisplayed()))
    }

    @Test fun uitest_verticalMirrorButton_isDisplayed() {
        onView(withId(R.id.verticalMirrorButton)).check(matches(isDisplayed()))
    }

    @Test fun uitest_horizontalMirrorButton_isDisplayed() {
        onView(withId(R.id.horizontalMirrorButton)).check(matches(isDisplayed()))
    }

    @Test fun uitest_darkenButton_isDisplayed() {
        onView(withId(R.id.darkenButton)).check(matches(isDisplayed()))
    }

    @Test fun uitest_lightenButton_isDisplayed() {
        onView(withId(R.id.lightenButton)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test fun uitest_clearAllButton_isDisplayed() {
        onView(withId(R.id.lightenButton)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test fun uitest_undoButton_isDisplayed() {
        onView(withId(R.id.lightenButton)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

    }

    @Test fun uitest_colorPickerButton_isDisplayed() {
        onView(withId(R.id.lightenButton)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test fun uitest_findAndReplaceButton_isDisplayed() {
        onView(withId(R.id.lightenButton)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }


    @Test fun uitest_eraseButton_isDisplayed() {
        onView(withId(R.id.lightenButton)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test fun uitest_userCanTapOnPixel() {
        for (i in 0..24) {
            onView(withId(R.id.canvasRecyclerView)).perform(actionOnItemAtPosition<RecyclerViewHolder>(i, click()))
        }
    }

    @Test fun uitest_userCanSelectColors() {
        for (i in ColourDatabase.toList().indices) {
            onView(withId(R.id.colourPickerRecyclerView)).perform(actionOnItemAtPosition<RecyclerViewHolder>(i, click()))
        }
    }

    @Test fun uitest_userCanSelectColorsAndTapOnPixel() {
        for (i in 0..24) {
            for (i2 in 0..4) {
                onView(withId(R.id.colourPickerRecyclerView)).perform(actionOnItemAtPosition<RecyclerViewHolder>(i2, click()))
                onView(withId(R.id.canvasRecyclerView)).perform(actionOnItemAtPosition<RecyclerViewHolder>(i, click()))
            }
        }
    }

    @Test fun uitest_userCanSwipeLeft() {
        onView(withId(R.id.canvasRecyclerView)).perform(swipeLeft())
    }

    @Test fun uitest_userCanSwipeRight() {
        onView(withId(R.id.canvasRecyclerView)).perform(swipeRight())
    }

    @Test fun uitest_userCanSwipeUp() {
        onView(withId(R.id.canvasRecyclerView)).perform(swipeUp())
    }

    @Test fun uitest_userCanSwipeDown() {
        onView(withId(R.id.canvasRecyclerView)).perform(swipeDown())
    }
}
