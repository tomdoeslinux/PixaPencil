package com.therealbluepandabear.pixapencil.mainactivity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

// Note:
// All times in ESPRESSO TEST DOCUMENTATION are in NZST

/**
 * Test completion summary for `MainActivityNewProjectFragmentTextInputLayoutErrorTests`:
 *
 * **Last completion of tests in this package file:**
 *
 * - 2022-05-02 21:46 (5/5 passed) on API 32
 *
 * **Last completion of tests in this package file for API 32:**
 * - 2022-05-02 21:46 (5/5 passed)
 */

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityNewProjectFragmentTextInputLayoutErrorTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val defaultProjectName = "Project"

    @Test
    fun clickOnNewProjectButtonThenCheckForEmptyNameError() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText("500"))
        onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText("1500"))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputLayout)).check(matches(hasDescendant(withText(
            R.string.exception_invalid_project_name_in_code_str
        ))))
    }

    @Test
    fun clickOnNewProjectButtonThenCheckForEmptyWidthError() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
        onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText("1500"))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_widthTextInputLayout)).check(matches(hasDescendant(withText(
            R.string.exception_empty_width_in_code_str
        ))))
    }

    @Test
    fun clickOnNewProjectButtonThenCheckForEmptyHeightError() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
        onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText("500"))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_heightTextInputLayout)).check(matches(hasDescendant(withText(
            R.string.exception_empty_height_in_code_str
        ))))
    }

    @Test
    fun clickOnNewProjectButtonThenCheckForOutOfRangeWidthError() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
        onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText("1500"))
        onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText("50000"))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_widthTextInputLayout)).check(matches(hasDescendant(withText(
            R.string.exception_invalid_width_in_code_str
        ))))
    }

    @Test
    fun clickOnNewProjectButtonThenCheckForOutOfRangeHeightError() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
        onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText("50000"))
        onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText("1500"))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_heightTextInputLayout)).check(matches(hasDescendant(withText(
            R.string.exception_invalid_height_in_code_str
        ))))
    }
}