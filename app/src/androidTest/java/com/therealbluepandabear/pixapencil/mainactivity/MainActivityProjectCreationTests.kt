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

    // Last successful test completion: 04-30 17:55 (API 32)
    // Last successful test completion on API 32: 04-30 17:55
    @Test
    fun create10x10() {
        createDummyProject(10, 10)
    }

    // Last successful test completion: 04-30 17:55 (API 32)
    // Last successful test completion on API 32: 04-30 17:55
    @Test
    fun create25x25() {
        createDummyProject(25, 25)
    }

    // Last successful test completion: 04-30 17:55 (API 32)
    // Last successful test completion on API 32: 04-30 17:55
    @Test
    fun create50x50() {
        createDummyProject(50, 50)
    }

    // Last successful test completion: 04-30 17:55 (API 32)
    // Last successful test completion on API 32: 04-30 17:55
    @Test
    fun create100x100() {
        createDummyProject(100, 100)
    }

    // Last successful test completion: 04-30 17:55 (API 32)
    // Last successful test completion on API 32: 04-30 17:55
    @Test
    fun create250x250() {
        createDummyProject(250, 250)
    }

    // Last successful test completion: 04-30 17:56 (API 32)
    // Last successful test completion on API 32: 04-30 17:56
    @Test
    fun create500x500() {
        createDummyProject(500, 500)
    }

    // Last successful test completion: 04-30 17:56 (API 32)
    // Last successful test completion on API 32: 04-30 17:56
    @Test
    fun create1000x1000() {
        createDummyProject(1000, 1000)
    }

    // Last successful test completion: 04-30 17:56 (API 32)
    // Last successful test completion on API 32: 04-30 17:56
    @Test
    fun create10x5() {
        createDummyProject(10, 5)
    }

    // Last successful test completion: 04-30 17:56 (API 32)
    // Last successful test completion on API 32: 04-30 17:56
    @Test
    fun create100x50() {
        createDummyProject(50, 100)
    }

    // Last successful test completion: 04-30 17:56 (API 32)
    // Last successful test completion on API 32: 04-30 17:56
    @Test
    fun create1000x500() {
        createDummyProject(500, 1000)
    }

    // Last successful test completion: 04-30 17:56 (API 32)
    // Last successful test completion on API 32: 04-30 17:56
    @Test
    fun create5x10() {
        createDummyProject(5, 10)
    }

    // Last successful test completion: 04-30 17:56 (API 32)
    // Last successful test completion on API 32: 04-30 17:56
    @Test
    fun create50x100() {
        createDummyProject(50, 100)
    }

    // Last successful test completion: 04-30 17:56 (API 32)
    // Last successful test completion on API 32: 04-30 17:56
    @Test
    fun create500x1000() {
        createDummyProject(500, 1000)
    }

}

