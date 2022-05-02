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

// Note:
// All times in ESPRESSO TEST DOCUMENTATION are in NZST

/**
 * Test completion summary for `MainActivityProjectCreationTests`:
 *
 * **Last completion of tests in this package file:**
 *
 * - 2022-05-02 21:48 (13/13 passed) on API 32
 *
 * **Last completion of tests in this package file for API 32:**
 * - 2022-05-02 21:48 (13/13 passed)
 */

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityProjectCreationTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val defaultProjectName = "Project"

    private fun createDummyProject(width: Int, height: Int) {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
        onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText(width.toString()))
        onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText(height.toString()))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
    }

    @Test
    fun create10x10() {
        createDummyProject(10, 10)
    }

    @Test
    fun create25x25() {
        createDummyProject(25, 25)
    }

    @Test
    fun create50x50() {
        createDummyProject(50, 50)
    }

    @Test
    fun create100x100() {
        createDummyProject(100, 100)
    }

    @Test
    fun create250x250() {
        createDummyProject(250, 250)
    }

    @Test
    fun create500x500() {
        createDummyProject(500, 500)
    }

    @Test
    fun create1000x1000() {
        createDummyProject(1000, 1000)
    }

    @Test
    fun create10x5() {
        createDummyProject(10, 5)
    }

    @Test
    fun create100x50() {
        createDummyProject(50, 100)
    }

    @Test
    fun create1000x500() {
        createDummyProject(500, 1000)
    }

    @Test
    fun create5x10() {
        createDummyProject(5, 10)
    }

    @Test
    fun create50x100() {
        createDummyProject(50, 100)
    }

    @Test
    fun create500x1000() {
        createDummyProject(500, 1000)
    }

}

