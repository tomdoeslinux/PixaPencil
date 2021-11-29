package com.realtomjoney.pyxlmoose.activities.`3_overnight_tests`

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.realtomjoney.pyxlmoose.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.realtomjoney.pyxlmoose.activities.main.MainActivity
import com.realtomjoney.pyxlmoose.database.ColourDatabase
import com.realtomjoney.pyxlmoose.viewholders.RecyclerViewHolder
import kotlin.random.Random


@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class OvernightStressTests {
    @get:Rule
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    val projectName = "Untitled Project"

    private fun createNewProject(boardSize: Int = 5) {
        onView(withId(R.id.floatingActionButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(replaceText(projectName))
        onView(withId(R.id.fragmentNewCanvas_spanCountTextInputEditText)).perform(replaceText(boardSize.toString()))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
    }

    private fun pickRandomColorFromColourPickerRecyclerViewAndThen(viewAction: ViewAction) {
        onView(withId(R.id.colourPickerRecyclerView)).perform(actionOnItemAtPosition<RecyclerViewHolder>(Random.nextInt(0, (ColourDatabase.toList().size - 1)), click()))
        onView(withId(R.id.canvasRecyclerView)).perform(viewAction)
    }

    private fun goBack() = onView(isRoot()).perform(pressBack())

    @Test fun uitest_canvasStressTest() {
        (1..30).forEach { i ->
            createNewProject(i)

            (1..20).forEach { _ ->
                pickRandomColorFromColourPickerRecyclerViewAndThen(swipeDown())
                pickRandomColorFromColourPickerRecyclerViewAndThen(swipeUp())
                pickRandomColorFromColourPickerRecyclerViewAndThen(swipeRight())
                pickRandomColorFromColourPickerRecyclerViewAndThen(swipeLeft())
            }

            (0 until i).forEach { i3 ->
                (ColourDatabase.toList().indices).forEach { i4 ->
                    onView(withId(R.id.colourPickerRecyclerView)).perform(actionOnItemAtPosition<RecyclerViewHolder>(i4, click()))
                    onView(withId(R.id.canvasRecyclerView)).perform(actionOnItemAtPosition<RecyclerViewHolder>(i3, click()))
                }
            }; goBack()
        }
    }

    @Test fun uitest_projectCreationStressTest() {
        (1..30).forEach { i ->
            createNewProject(i)
            onView(withId(R.id.doneButton)).perform(click())
            goBack()
        }
    }
}
