package com.realtomjoney.pyxlmoose.activities.`2_canvas`

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.realtomjoney.pyxlmoose.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.realtomjoney.pyxlmoose.AndroidTestUtilityFunctions
import com.realtomjoney.pyxlmoose.activities.canvas.CanvasActivity

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class CanvasActivityTestsOnFindAndReplaceButtonTap {
    @get:Rule
    var activityTestRule = ActivityScenarioRule(CanvasActivity::class.java)

    @Test fun uitest_doneButton_isNotDisplayed_after_findAndReplaceButton_isPressed() {
        AndroidTestUtilityFunctions.goToFindAndReplaceFragment()
        onView(withId(R.id.activityCanvas_doneButton)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test fun uitest_colourPickerRecyclerView_isNotDisplayed_after_findAndReplaceButton_isPressed() {
        AndroidTestUtilityFunctions.goToFindAndReplaceFragment()
        onView(withId(R.id.activityCanvas_colorPickerRecyclerView)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test fun uitest_titleTextView_isNotDisplayed_after_findAndReplaceButton_isPressed() {
        AndroidTestUtilityFunctions.goToFindAndReplaceFragment()
        onView(withId(R.id.activityCanvas_canvasTitleEditText)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test fun uitest_colorSwapButton_isNotDisplayed_after_findAndReplaceButton_isPressed() {
        AndroidTestUtilityFunctions.goToFindAndReplaceFragment()
        onView(withId(R.id.activityCanvas_colorSwapButton)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test fun uitest_colourPrimarySelected_isNotDisplayed_after_findAndReplaceButton_isPressed() {
        AndroidTestUtilityFunctions.goToFindAndReplaceFragment()
        onView(withId(R.id.activityCanvas_colorPrimaryView)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test fun uitest_colourSecondarySelected_isNotDisplayed_after_findAndReplaceButton_isPressed() {
        AndroidTestUtilityFunctions.goToFindAndReplaceFragment()
        onView(withId(R.id.activityCanvas_colorSecondaryView)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test fun uitest_fragmentFindAndReplaceAvailableColorsRecyclerView_isDisplayed_after_findAndReplaceButton_isPressed() {
        AndroidTestUtilityFunctions.goToFindAndReplaceFragment()
        onView(withId(R.id.fragmentFindAndReplace_availableColorsRecyclerView)).check(matches(isDisplayed()))
    }

    @Test fun uitest_fragmentFindAndReplaceCanvasColorsRecyclerView_isDisplayed_after_findAndReplaceButton_isPressed() {
        AndroidTestUtilityFunctions.goToFindAndReplaceFragment()
        onView(withId(R.id.fragmentFindAndReplace_canvasColorsRecyclerView)).check(matches(isDisplayed()))
    }

    @Test fun uitest_fragmentFindAndReplaceColorToFind_isDisplayed_after_findAndReplaceButton_isPressed() {
        AndroidTestUtilityFunctions.goToFindAndReplaceFragment()
        onView(withId(R.id.fragmentFindAndReplace_colorToFind)).check(matches(isDisplayed()))
    }

    @Test fun uitest_fragmentFindAndReplaceColorToReplace_isDisplayed_after_findAndReplaceButton_isPressed() {
        AndroidTestUtilityFunctions.goToFindAndReplaceFragment()
        onView(withId(R.id.fragmentFindAndReplace_colorToReplace)).check(matches(isDisplayed()))
    }

    @Test fun uitest_fragmentFindAndReplaceColorsInCanvasTextView_isDisplayed_after_findAndReplaceButton_isPressed() {
        AndroidTestUtilityFunctions.goToFindAndReplaceFragment()
        onView(withId(R.id.fragmentFindAndReplace_colorsInCanvasTextView)).check(matches(isDisplayed()))
    }

    @Test fun uitest_fragmentFindAndReplaceDoneButton_isDisplayed_after_findAndReplaceButton_isPressed() {
        AndroidTestUtilityFunctions.goToFindAndReplaceFragment()
        onView(withId(R.id.fragmentFindAndReplace_doneButton)).check(matches(isDisplayed()))
    }

    @Test fun uitest_fragmentFindAndReplaceFindTextView_isDisplayed_after_findAndReplaceButton_isPressed() {
        AndroidTestUtilityFunctions.goToFindAndReplaceFragment()
        onView(withId(R.id.fragmentFindAndReplace_findTextView)).check(matches(isDisplayed()))
    }

    @Test fun uitest_fragmentFindAndReplaceReplaceWithTextView_isDisplayed_after_findAndReplaceButton_isPressed() {
        AndroidTestUtilityFunctions.goToFindAndReplaceFragment()
        onView(withId(R.id.fragmentFindAndReplace_replaceWithTextView)).check(matches(isDisplayed()))
    }
}