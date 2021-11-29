package com.realtomjoney.pyxlmoose.activities.`1_main`

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.realtomjoney.pyxlmoose.R
import org.junit.Rule
import org.junit.Test
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.realtomjoney.pyxlmoose.activities.main.MainActivity
import com.realtomjoney.pyxlmoose.viewholders.RecyclerViewHolder
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTestsWhenNewProjectIsCreated {
    @get:Rule
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    private fun createNewProject(boardSize: Int = 5) {
        onView(withId(R.id.floatingActionButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(ViewActions.replaceText("Untitled Project"))
        onView(withId(R.id.fragmentNewCanvas_spanCountTextInputEditText)).perform(ViewActions.replaceText(boardSize.toString()))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
    }

    @Test fun uitest_fragmentNewCanvasProjectTitleTextInputEditText_isNotDisplayed_after_fragmentNewCanvasDoneButton_isPressed() {
        createNewProject()
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).check(ViewAssertions.doesNotExist())
    }

    @Test fun uitest_fragmentNewCanvasProjectTitleTextInputLayout_isNotDisplayed_after_fragmentNewCanvasDoneButton_isPressed() {
        createNewProject()
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputLayout)).check(ViewAssertions.doesNotExist())
    }

    @Test fun uitest_fragmentNewCanvasSpanCountTextInputEditText_isNotDisplayed_after_fragmentNewCanvasDoneButton_isPressed() {
        createNewProject()
        onView(withId(R.id.fragmentNewCanvas_spanCountTextInputEditText)).check(ViewAssertions.doesNotExist())
    }

    @Test fun uitest_fragmentNewCanvasSpanCountTextInputLayout_isNotDisplayed_after_fragmentNewCanvasDoneButton_isPressed() {
        createNewProject()
        onView(withId(R.id.fragmentNewCanvas_spanCountTextInputLayout)).check(ViewAssertions.doesNotExist())
    }

    @Test fun uitest_fragmentNewCanvasDoneButton_isNotDisplayed_after_fragmentNewCanvasDoneButton_isPressed() {
        createNewProject()
        onView(withId(R.id.fragmentNewCanvas_doneButton)).check(ViewAssertions.doesNotExist())
    }

    @Test fun uitest_canvasStressTest() {
        for (i in 1..100) {
            createNewProject(i)

            for (i2 in 1..20) {
                onView(withId(R.id.canvasRecyclerView)).perform(ViewActions.swipeDown())
                onView(withId(R.id.canvasRecyclerView)).perform(ViewActions.swipeUp())
                onView(withId(R.id.canvasRecyclerView)).perform(ViewActions.swipeRight())
                onView(withId(R.id.canvasRecyclerView)).perform(ViewActions.swipeLeft())
            }

            for (i3 in 0 until i) {
                for (i4 in 0..4) {
                    onView(withId(R.id.colourPickerRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerViewHolder>(i4, click()))
                    onView(withId(R.id.canvasRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerViewHolder>(i3, click()))
                }
            }

            onView(isRoot()).perform(pressBack())
        }
    }
}
