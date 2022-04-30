package com.therealbluepandabear.pixapencil.mainactivity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
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
class MainActivityViewDisplayedTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkRecyclerViewIsDisplayed() {
        onView(withId(R.id.activityMain_recentCreationsRecyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun checkNewProjectButtonIsDisplayed() {
        onView(withId(R.id.activityMain_newProjectButton)).check(matches(isDisplayed()))
    }

    @Test
    fun checkBottomNavigationMenuStarredTabIsDisplayed() {
        onView(withId(R.id.activityMainBottomNavigationMenu_starred_tab)).check(matches(isDisplayed()))
    }

    @Test
    fun checkBottomNavigationMenuHomeTabIsDisplayed() {
        onView(withId(R.id.activityMainBottomNavigationMenu_home_tab)).check(matches(isDisplayed()))
    }

    @Test
    fun checkTopAppMenuCommunityItemIsDisplayed() {
        onView(withId(R.id.activityMainTopAppMenu_community_item)).check(matches(isDisplayed()))
    }

    @Test
    fun checkBottomNavigationViewIsDisplayed() {
        onView(withId(R.id.activityMain_bottomNavigationView)).check(matches(isDisplayed()))
    }

    @Test
    fun checkPrimaryFragmentHostIsDisplayed() {
        onView(withId(R.id.activityMain_primaryFragmentHost)).check(matches(isDisplayed()))
    }
}