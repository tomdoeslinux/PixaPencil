package com.therealbluepandabear.pixapencil.mainactivity

import androidx.test.espresso.Espresso.onView
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

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityBottomTabTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    // Last successful test completion: 04-30 17:28 (API 32)
    // Last successful test completion on API 32: 04-30 17:28
    @Test
    fun clickOnHomeTab() {
        onView(withId(R.id.activityMainBottomNavigationMenu_home_tab)).perform(click())
    }

    // Last successful test completion: 04-30 17:27 (API 32)
    // Last successful test completion on API 32: 04-30 17:27
    @Test
    fun clickOnHomeTab25Times() {
        for (i in 0..25) {
            onView(withId(R.id.activityMainBottomNavigationMenu_home_tab)).perform(click())
        }
    }

    // Last successful test completion: 04-30 17:31 (API 32)
    // Last successful test completion on API 32: 04-30 17:31
    @Test
    fun clickOnHomeTab50Times() {
        for (i in 0..50) {
            onView(withId(R.id.activityMainBottomNavigationMenu_home_tab)).perform(click())
        }
    }

    // Last successful test completion: 04-30 17:29 (API 32)
    // Last successful test completion on API 32: 04-30 17:29
    @Test
    fun clickOnHomeTab100Times() {
        for (i in 0..100) {
            onView(withId(R.id.activityMainBottomNavigationMenu_home_tab)).perform(click())
        }
    }

    // Last successful test completion: 04-30 17:27 (API 32)
    // Last successful test completion on API 32: 04-30 17:27
    @Test
    fun clickOnStarredTab25Times() {
        for (i in 0..25) {
            onView(withId(R.id.activityMainBottomNavigationMenu_starred_tab)).perform(click())
        }
    }

    // Last successful test completion: 04-30 17:32 (API 32)
    // Last successful test completion on API 32: 04-30 17:32
    @Test
    fun clickOnStarredTab50Times() {
        for (i in 0..50) {
            onView(withId(R.id.activityMainBottomNavigationMenu_starred_tab)).perform(click())
        }
    }

    // Last successful test completion: 04-30 17:28 (API 32)
    // Last successful test completion on API 32: 04-30 17:28
    @Test
    fun clickOnStarredTab100Times() {
        for (i in 0..100) {
            onView(withId(R.id.activityMainBottomNavigationMenu_starred_tab)).perform(click())
        }
    }

    // Last successful test completion: 04-30 17:27 (API 32)
    // Last successful test completion on API 32: 04-30 17:27
    @Test
    fun switchBetweenHomeAndStarredTab() {
        onView(withId(R.id.activityMainBottomNavigationMenu_home_tab)).perform(click())
        onView(withId(R.id.activityMainBottomNavigationMenu_starred_tab)).perform(click())
    }

    // Last successful test completion: 04-30 17:27 (API 32)
    // Last successful test completion on API 32: 04-30 17:27
    @Test
    fun switchBetweenHomeAndStarredTab25Times() {
        for (i in 0..25) {
            onView(withId(R.id.activityMainBottomNavigationMenu_home_tab)).perform(click())
            onView(withId(R.id.activityMainBottomNavigationMenu_starred_tab)).perform(click())
        }
    }

    // Last successful test completion: 04-30 17:32 (API 32)
    // Last successful test completion on API 32: 04-30 17:32
    @Test
    fun switchBetweenHomeAndStarredTab50Times() {
        for (i in 0..50) {
            onView(withId(R.id.activityMainBottomNavigationMenu_home_tab)).perform(click())
            onView(withId(R.id.activityMainBottomNavigationMenu_starred_tab)).perform(click())
        }
    }

    // Last successful test completion: 04-30 17:31 (API 32)
    // Last successful test completion on API 32: 04-30 17:31
    @Test
    fun switchBetweenHomeAndStarredTab100Times() {
        for (i in 0..100) {
            onView(withId(R.id.activityMainBottomNavigationMenu_home_tab)).perform(click())
            onView(withId(R.id.activityMainBottomNavigationMenu_starred_tab)).perform(click())
        }
    }

    // Last successful test completion: 04-30 17:29 (API 32)
    // Last successful test completion on API 32: 04-30 17:29
    @Test
    fun switchBetweenStarredAndHomeTab() {
        onView(withId(R.id.activityMainBottomNavigationMenu_starred_tab)).perform(click())
        onView(withId(R.id.activityMainBottomNavigationMenu_home_tab)).perform(click())
    }

    // Last successful test completion: 04-30 17:34 (API 32)
    // Last successful test completion on API 32: 04-30 17:34
    @Test
    fun switchBetweenStarredAndHomeTab25Times() {
        for (i in 0..25) {
            onView(withId(R.id.activityMainBottomNavigationMenu_starred_tab)).perform(click())
            onView(withId(R.id.activityMainBottomNavigationMenu_home_tab)).perform(click())
        }
    }

    // Last successful test completion: 04-30 17:29 (API 32)
    // Last successful test completion on API 32: 04-30 17:29
    @Test
    fun switchBetweenStarredAndHomeTab50Times() {
        for (i in 0..50) {
            onView(withId(R.id.activityMainBottomNavigationMenu_starred_tab)).perform(click())
            onView(withId(R.id.activityMainBottomNavigationMenu_home_tab)).perform(click())
        }
    }

    // Last successful test completion: 04-30 17:34 (API 32)
    // Last successful test completion on API 32: 04-30 17:34
    @Test
    fun switchBetweenStarredAndHomeTab100Times() {
        for (i in 0..100) {
            onView(withId(R.id.activityMainBottomNavigationMenu_starred_tab)).perform(click())
            onView(withId(R.id.activityMainBottomNavigationMenu_home_tab)).perform(click())
        }
    }
}