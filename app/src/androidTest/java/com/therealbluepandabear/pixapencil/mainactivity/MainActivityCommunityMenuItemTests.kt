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