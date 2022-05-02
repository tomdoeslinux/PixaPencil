package com.therealbluepandabear.pixapencil.mainactivity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
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
 * Test completion summary for `MainActivityCommunityMenuItemTests`:
 *
 * **Last completion of tests in this package file:**
 *
 * - 2022-05-02 21:26 (4/4 passed) on API 32
 *
 * **Last completion of tests in this package file for API 32:**
 * - 2022-05-02 21:26 (4/4 passed)
 */

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityCommunityMenuItemTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun clickOnCommunityItem() {
        onView(withId(R.id.activityMainTopAppMenu_save_project_item)).perform(click())
    }

    @Test
    fun clickOnCommunityItem25Times() {
        for (i in 0..25) {
            onView(withId(R.id.activityMainTopAppMenu_save_project_item)).perform(click())
            pressBack()
        }
    }

    @Test
    fun clickOnCommunityItem50Times() {
        for (i in 0..50) {
            onView(withId(R.id.activityMainTopAppMenu_save_project_item)).perform(click())
            pressBack()
        }
    }

    @Test
    fun clickOnCommunityItem100Times() {
        for (i in 0..100) {
            onView(withId(R.id.activityMainTopAppMenu_save_project_item)).perform(click())
            pressBack()
        }
    }
}