package com.realtomjoney.pyxlmoose.activities.`2_overnight_tests`

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
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
import com.realtomjoney.pyxlmoose.activities.main.MainActivity
import com.realtomjoney.pyxlmoose.database.ColourDatabase
import com.realtomjoney.pyxlmoose.viewholders.RecyclerViewHolder
import kotlin.random.Random


@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class OvernightStressTests {
    @get:Rule
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    private fun createNewProject(boardSize: Int = 5) {
        onView(withId(R.id.floatingActionButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(replaceText("Untitled Project"))
        onView(withId(R.id.fragmentNewCanvas_spanCountTextInputEditText)).perform(replaceText(boardSize.toString()))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
    }

    private fun pickRandomColorFromColourPickerRecyclerView() {
        onView(withId(R.id.colourPickerRecyclerView)).perform(actionOnItemAtPosition<RecyclerViewHolder>(
            Random.nextInt(0, (ColourDatabase.toList().size - 1)), click()))
    }

    @Test fun uitest_canvasStressTest() {
        for (i in 1..100) {
            createNewProject(i)

            for (i2 in 1..20) {
                pickRandomColorFromColourPickerRecyclerView()
                onView(withId(R.id.canvasRecyclerView)).perform(swipeDown())

                pickRandomColorFromColourPickerRecyclerView()
                onView(withId(R.id.canvasRecyclerView)).perform(swipeUp())

                pickRandomColorFromColourPickerRecyclerView()
                onView(withId(R.id.canvasRecyclerView)).perform(swipeRight())

                pickRandomColorFromColourPickerRecyclerView()
                onView(withId(R.id.canvasRecyclerView)).perform(swipeLeft())
            }

            for (i3 in 0 until i) {
                for (i4 in ColourDatabase.toList().indices) {
                    onView(withId(R.id.colourPickerRecyclerView)).perform(actionOnItemAtPosition<RecyclerViewHolder>(i4, click()))
                    onView(withId(R.id.canvasRecyclerView)).perform(actionOnItemAtPosition<RecyclerViewHolder>(i3, click()))
                }
            }

            onView(isRoot()).perform(pressBack())
        }
    }
}
