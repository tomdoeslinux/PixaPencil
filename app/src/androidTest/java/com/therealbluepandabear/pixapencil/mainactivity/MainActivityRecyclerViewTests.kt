package com.therealbluepandabear.pixapencil.mainactivity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityRecyclerViewTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val defaultProjectName = "Project"
    private val defaultProjectWidth = 10
    private val defaultProjectHeight = 10

    @Test
    fun canRecyclerViewHandle1Project() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
        onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText(defaultProjectWidth.toString()))
        onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText(defaultProjectHeight.toString()))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())

        onView(withId(R.id.activityMainTopAppMenu_community_item)).perform(click())
    }

    @Test
    fun canRecyclerViewHandle25Projects() {
        for (i in 0..25) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
            onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText(defaultProjectWidth.toString()))
            onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText(defaultProjectHeight.toString()))
            onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
            onView(withId(R.id.activityMainTopAppMenu_community_item)).perform(click())
        }
    }

    @Test
    fun canRecyclerViewHandle50Projects() {
        for (i in 0..50) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
            onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText(defaultProjectWidth.toString()))
            onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText(defaultProjectHeight.toString()))
            onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
            onView(withId(R.id.activityMainTopAppMenu_community_item)).perform(click())
        }
    }

    @Test
    fun canRecyclerViewHandle100Projects() {
        for (i in 0..100) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
            onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText(defaultProjectWidth.toString()))
            onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText(defaultProjectHeight.toString()))
            onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
            onView(withId(R.id.activityMainTopAppMenu_community_item)).perform(click())
        }
    }

    @Test
    fun canRecyclerViewHandle250Projects() {
        for (i in 0..250) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
            onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText(defaultProjectWidth.toString()))
            onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText(defaultProjectHeight.toString()))
            onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
            onView(withId(R.id.activityMainTopAppMenu_community_item)).perform(click())
        }
    }

    @Test
    fun canRecyclerViewHandle500Projects() {
        for (i in 0..500) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
            onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText(defaultProjectWidth.toString()))
            onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText(defaultProjectHeight.toString()))
            onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
            onView(withId(R.id.activityMainTopAppMenu_community_item)).perform(click())
        }
    }

    @Test
    fun canRecyclerViewHandle1000Projects() {
        for (i in 0..1000) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
            onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText(defaultProjectWidth.toString()))
            onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText(defaultProjectHeight.toString()))
            onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
            onView(withId(R.id.activityMainTopAppMenu_community_item)).perform(click())
        }
    }

    @Test
    fun canRecyclerViewHandle2000Projects() {
        for (i in 0..2000) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
            onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText(defaultProjectWidth.toString()))
            onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText(defaultProjectHeight.toString()))
            onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
            onView(withId(R.id.activityMainTopAppMenu_community_item)).perform(click())
        }
    }


}