package com.realtomjoney.pyxlmoose.activities.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.realtomjoney.pyxlmoose.R
import org.junit.Rule
import org.junit.Test
import androidx.test.espresso.action.ViewActions.click
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTestsOnFABTap {
    @get:Rule
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun uitest_fragmentNewCanvasRootLayout_isDisplayed() {
        onView(withId(R.id.floatingActionButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_rootLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun uitest_fragmentNewCanvasProjectTitleTextInputEditText_isDisplayed() {
        onView(withId(R.id.floatingActionButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).check(matches(isDisplayed()))
    }

    @Test
    fun uitest_fragmentNewCanvasProjectTitleTextInputLayout_isDisplayed() {
        onView(withId(R.id.floatingActionButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun uitest_fragmentNewCanvasSpanCountTextInputEditText_isDisplayed() {
        onView(withId(R.id.floatingActionButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_spanCountTextInputEditText)).check(matches(isDisplayed()))
    }

    @Test
    fun uitest_fragmentNewCanvasSpanCountTextInputLayout_isDisplayed() {
        onView(withId(R.id.floatingActionButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_spanCountTextInputLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun uitest_fragmentNewCanvasDoneButton_isDisplayed() {
        onView(withId(R.id.floatingActionButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_doneButton)).check(matches(isDisplayed()))
    }
}
