package com.realtomjoney.pyxlmoose.activities.`3_overnight_tests`

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.realtomjoney.pyxlmoose.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.realtomjoney.pyxlmoose.AndroidTestUtilityFunctions
import com.realtomjoney.pyxlmoose.activities.main.MainActivity
import com.realtomjoney.pyxlmoose.database.ColorDatabase
import com.realtomjoney.pyxlmoose.viewholders.RecyclerViewHolder
import kotlin.random.Random


@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class OvernightStressTests {
    @get:Rule
    var activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    private fun pickRandomColorFromColourPickerRecyclerViewAndThen(viewAction: ViewAction) {
        onView(withId(R.id.activityCanvas_colorPickerRecyclerView)).perform(actionOnItemAtPosition<RecyclerViewHolder>(Random.nextInt(0, (ColorDatabase.toList().size - 1)), click()))
        onView(withId(R.id.canvasRecyclerView)).perform(viewAction)
    }

    @Test fun uitest_canvasStressTest() {
        (1..30).forEach { i ->
            AndroidTestUtilityFunctions.createNewProject("Unnamed Project", i)

            (1..20).forEach { _ ->
                pickRandomColorFromColourPickerRecyclerViewAndThen(swipeDown())
                pickRandomColorFromColourPickerRecyclerViewAndThen(swipeUp())
                pickRandomColorFromColourPickerRecyclerViewAndThen(swipeRight())
                pickRandomColorFromColourPickerRecyclerViewAndThen(swipeLeft())
            }

            (0 until i).forEach { i3 ->
                (ColorDatabase.toList().indices).forEach { i4 ->
                    onView(withId(R.id.activityCanvas_colorPickerRecyclerView)).perform(actionOnItemAtPosition<RecyclerViewHolder>(i4, click()))
                    onView(withId(R.id.canvasRecyclerView)).perform(actionOnItemAtPosition<RecyclerViewHolder>(i3, click()))
                }
            }
            AndroidTestUtilityFunctions.goBack()
        }
    }

    @Test fun uitest_projectCreationStressTest() {
        (1..30).forEach { i ->
            AndroidTestUtilityFunctions.createNewProject("Unnamed Project", i)
            onView(withId(R.id.activityCanvas_doneButton)).perform(click())
            AndroidTestUtilityFunctions.goBack()
        }
    }
}
