package com.realtomjoney.pyxlmoose.activities.`2_canvas`

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.realtomjoney.pyxlmoose.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.realtomjoney.pyxlmoose.activities.canvas.CanvasActivity
import com.realtomjoney.pyxlmoose.database.ColorDatabase
import com.realtomjoney.pyxlmoose.viewholders.RecyclerViewHolder


@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class CanvasActivityTest {
    @get:Rule
    var activityTestRule = ActivityScenarioRule(CanvasActivity::class.java)

    @Test fun uitest_doneButtonFAB_isDisplayed() {
        onView(withId(R.id.activityCanvas_doneButton)).check(matches(isDisplayed()))
    }

    @Test fun uitest_colourPickerRecyclerView_isDisplayed() {
        onView(withId(R.id.activityCanvas_colorPickerRecyclerView)).check(matches(isDisplayed()))
    }

    @Test fun uitest_titleTextView_isDisplayed() {
        onView(withId(R.id.activityCanvas_canvasTitleEditText)).check(matches(isDisplayed()))
    }

    @Test fun uitest_colorSwapButton_isDisplayed() {
        onView(withId(R.id.activityCanvas_colorSwapButton)).check(matches(isDisplayed()))
    }

    @Test fun uitest_colourPrimarySelected_isDisplayed() {
        onView(withId(R.id.activityCanvas_colorPrimaryView)).check(matches(isDisplayed()))
    }

    @Test fun uitest_colourSecondarySelected_isDisplayed() {
        onView(withId(R.id.activityCanvas_colorSecondaryView)).check(matches(isDisplayed()))
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
        onView(withId(R.id.clearAllButton)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test fun uitest_undoButton_isDisplayed() {
        onView(withId(R.id.undoButton)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

    }

    @Test fun uitest_colorPickerButton_isDisplayed() {
        onView(withId(R.id.colorPickerButton)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test fun uitest_findAndReplaceButton_isDisplayed() {
        onView(withId(R.id.findAndReplaceButton)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }


    @Test fun uitest_eraseButton_isDisplayed() {
        onView(withId(R.id.eraseButton)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test fun uitest_userCanTapOnPixel() {
        for (i in 0..24) {
            onView(withId(R.id.canvasRecyclerView)).perform(actionOnItemAtPosition<RecyclerViewHolder>(i, click()))
        }
    }

    @Test fun uitest_userCanSelectColors() {
        for (i in ColorDatabase.toList().indices) {
            onView(withId(R.id.activityCanvas_colorPickerRecyclerView)).perform(actionOnItemAtPosition<RecyclerViewHolder>(i, click()))
        }
    }

    @Test fun uitest_userCanSelectColorsAndTapOnPixel() {
        for (i in 0..24) {
            for (i2 in 0..4) {
                onView(withId(R.id.activityCanvas_colorPickerRecyclerView)).perform(actionOnItemAtPosition<RecyclerViewHolder>(i2, click()))
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
