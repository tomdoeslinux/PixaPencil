package com.therealbluepandabear.pixapencil.mainactivity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.activities.main.binding
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

    private fun createDummyProject() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
        onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText(defaultProjectWidth.toString()))
        onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText(defaultProjectHeight.toString()))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())

        onView(withId(R.id.activityMainTopAppMenu_community_item)).perform(click())
    }

    private fun getRecyclerViewItemCount(): Int {
        var itemCount = 0

        activityRule.scenario.onActivity {
            itemCount = binding.activityMainRecentCreationsRecyclerView.adapter!!.itemCount
        }

        return itemCount
    }

    private fun scrollBackAndForth() {
        onView(withId(R.id.activityMain_recentCreationsRecyclerView)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(getRecyclerViewItemCount() - 1))
        onView(withId(R.id.activityMain_recentCreationsRecyclerView)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
    }

    // Last completed successfully on April 29 at 9:24 PM
    @Test
    fun canHandle1Project() {
        createDummyProject()
    }
    
    // Last completed successfully on April 29 at 9:24 PM
    @Test
    fun canHandle25Projects() {
        for (i in 0..25) {
            createDummyProject()
        }
    }

    @Test
    fun canHandle50Projects() {
        for (i in 0..50) {
            createDummyProject()
        }
    }

    @Test
    fun canHandle100Projects() {
        for (i in 0..100) {
            createDummyProject()
        }
    }

    @Test
    fun canHandle250Projects() {
        for (i in 0..250) {
            createDummyProject()
        }
    }

    @Test
    fun canHandle500Projects() {
        for (i in 0..500) {
            createDummyProject()
        }
    }

    @Test
    fun canHandle1000Projects() {
        for (i in 0..1000) {
            createDummyProject()
        }
    }

    @Test
    fun canHandle2000Projects() {
        for (i in 0..2000) {
            createDummyProject()
        }
    }

    @Test
    fun canHandleScrollingBackAndForth50TimesWith1Project() {
        for (i in 0..50) {
            scrollBackAndForth()
        }
    }

    @Test
    fun canHandleScrollingBackAndForth50TimesWith25Projects() {
        for (i in 0..25) {
            createDummyProject()
        }

        for (i in 0..50) {
            scrollBackAndForth()
        }
    }

    @Test
    fun canHandleScrollingBackAndForth50TimesWith50Projects() {
        for (i in 0..50) {
            createDummyProject()
        }

        for (i in 0..50) {
            scrollBackAndForth()
        }
    }

    @Test
    fun canHandleScrollingBackAndForth50TimesWith100Projects() {
        for (i in 0..100) {
            createDummyProject()
        }

        for (i in 0..50) {
            scrollBackAndForth()
        }
    }

    @Test
    fun canHandleScrollingBackAndForth50TimesWith250Projects() {
        for (i in 0..250) {
            createDummyProject()
        }

        for (i in 0..50) {
            scrollBackAndForth()
        }
    }

    @Test
    fun canHandleScrollingBackAndForth50TimesWith500Projects() {
        for (i in 0..500) {
            createDummyProject()
        }

        for (i in 0..50) {
            scrollBackAndForth()
        }
    }

    @Test
    fun canHandleScrollingBackAndForth100TimesWith1000Projects() {
        for (i in 0..1000) {
            createDummyProject()
        }

        for (i in 0..50) {
            scrollBackAndForth()
        }
    }

    @Test
    fun canHandleScrollingBackAndForth200TimesWith2000Projects() {
        for (i in 0..2000) {
            createDummyProject()
        }

        for (i in 0..50) {
            scrollBackAndForth()
        }
    }

    @Test
    fun canHandleScrollingBackAndForth150TimesWith50Projects() {
        for (i in 0..50) {
            createDummyProject()
        }

        for (i in 0..150) {
            scrollBackAndForth()
        }
    }

    @Test
    fun canHandleScrollingBackAndForth150TimesWith100Projects() {
        for (i in 0..100) {
            createDummyProject()
        }

        for (i in 0..150) {
            scrollBackAndForth()
        }
    }

    @Test
    fun canHandleScrollingBackAndForth150TimesWith250Projects() {
        for (i in 0..250) {
            createDummyProject()
        }

        for (i in 0..150) {
            scrollBackAndForth()
        }
    }

    @Test
    fun canHandleScrollingBackAndForth150TimesWith500Projects() {
        for (i in 0..500) {
            createDummyProject()
        }

        for (i in 0..150) {
            scrollBackAndForth()
        }
    }

    @Test
    fun canHandleScrollingBackAndForth150TimesWith1000Projects() {
        for (i in 0..1000) {
            createDummyProject()
        }

        for (i in 0..150) {
            scrollBackAndForth()
        }
    }

    @Test
    fun canHandleScrollingBackAndForth150TimesWith2000Projects() {
        for (i in 0..2000) {
            createDummyProject()
        }

        for (i in 0..150) {
            scrollBackAndForth()
        }
    }
}