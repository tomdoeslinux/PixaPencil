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

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityBottomTabTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun clickOnHomeTab() {
        onView(withId(R.id.activityMainBottomNavigationMenu_home_tab)).perform(click())
    }

    @Test
    fun clickOnHomeTab25Times() {
        for (i in 0..25) {
            onView(withId(R.id.activityMainBottomNavigationMenu_home_tab)).perform(click())
        }
    }

    @Test
    fun clickOnHomeTab50Times() {
        for (i in 0..50) {
            onView(withId(R.id.activityMainBottomNavigationMenu_home_tab)).perform(click())
        }
    }

    @Test
    fun clickOnHomeTab100Times() {
        for (i in 0..100) {
            onView(withId(R.id.activityMainBottomNavigationMenu_home_tab)).perform(click())
        }
    }

    @Test
    fun clickOnStarredTab25Times() {
        for (i in 0..25) {
            onView(withId(R.id.activityMainBottomNavigationMenu_starred_tab)).perform(click())
        }
    }

    @Test
    fun clickOnStarredTab50Times() {
        for (i in 0..50) {
            onView(withId(R.id.activityMainBottomNavigationMenu_starred_tab)).perform(click())
        }
    }

    @Test
    fun clickOnStarredTab100Times() {
        for (i in 0..100) {
            onView(withId(R.id.activityMainBottomNavigationMenu_starred_tab)).perform(click())
        }
    }

    @Test
    fun switchBetweenHomeAndStarredTab() {
        onView(withId(R.id.activityMainBottomNavigationMenu_home_tab)).perform(click())
        onView(withId(R.id.activityMainBottomNavigationMenu_starred_tab)).perform(click())
    }

    @Test
    fun switchBetweenHomeAndStarredTab25Times() {
        for (i in 0..25) {
            onView(withId(R.id.activityMainBottomNavigationMenu_home_tab)).perform(click())
            onView(withId(R.id.activityMainBottomNavigationMenu_starred_tab)).perform(click())
        }
    }

    @Test
    fun switchBetweenHomeAndStarredTab50Times() {
        for (i in 0..50) {
            onView(withId(R.id.activityMainBottomNavigationMenu_home_tab)).perform(click())
            onView(withId(R.id.activityMainBottomNavigationMenu_starred_tab)).perform(click())
        }
    }

    @Test
    fun switchBetweenHomeAndStarredTab100Times() {
        for (i in 0..100) {
            onView(withId(R.id.activityMainBottomNavigationMenu_home_tab)).perform(click())
            onView(withId(R.id.activityMainBottomNavigationMenu_starred_tab)).perform(click())
        }
    }

    @Test
    fun switchBetweenStarredAndHomeTab() {
        onView(withId(R.id.activityMainBottomNavigationMenu_starred_tab)).perform(click())
        onView(withId(R.id.activityMainBottomNavigationMenu_home_tab)).perform(click())
    }

    @Test
    fun switchBetweenStarredAndHomeTab25Times() {
        for (i in 0..25) {
            onView(withId(R.id.activityMainBottomNavigationMenu_starred_tab)).perform(click())
            onView(withId(R.id.activityMainBottomNavigationMenu_home_tab)).perform(click())
        }
    }

    @Test
    fun switchBetweenStarredAndHomeTab50Times() {
        for (i in 0..50) {
            onView(withId(R.id.activityMainBottomNavigationMenu_starred_tab)).perform(click())
            onView(withId(R.id.activityMainBottomNavigationMenu_home_tab)).perform(click())
        }
    }

    @Test
    fun switchBetweenStarredAndHomeTab100Times() {
        for (i in 0..100) {
            onView(withId(R.id.activityMainBottomNavigationMenu_starred_tab)).perform(click())
            onView(withId(R.id.activityMainBottomNavigationMenu_home_tab)).perform(click())
        }
    }
}