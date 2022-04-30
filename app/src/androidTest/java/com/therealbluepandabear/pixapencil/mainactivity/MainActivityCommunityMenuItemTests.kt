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

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityCommunityMenuItemTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    // Last successful test completion: 04-30 17:36 (API 32)
    // Last successful test completion on API 32: 04-30 17:36
    @Test
    fun clickOnCommunityItem() {
        onView(withId(R.id.activityMainTopAppMenu_community_item)).perform(click())
    }

    // Last successful test completion: 04-30 17:35 (API 32)
    // Last successful test completion on API 32: 04-30 17:35
    @Test
    fun clickOnCommunityItem25Times() {
        for (i in 0..25) {
            onView(withId(R.id.activityMainTopAppMenu_community_item)).perform(click())
            pressBack()
        }
    }

    // Last successful test completion: 04-30 17:35 (API 32)
    // Last successful test completion on API 32: 04-30 17:35
    @Test
    fun clickOnCommunityItem50Times() {
        for (i in 0..50) {
            onView(withId(R.id.activityMainTopAppMenu_community_item)).perform(click())
            pressBack()
        }
    }

    // Last successful test completion: 04-30 17:36 (API 32)
    // Last successful test completion on API 32: 04-30 17:36
    @Test
    fun clickOnCommunityItem100Times() {
        for (i in 0..100) {
            onView(withId(R.id.activityMainTopAppMenu_community_item)).perform(click())
            pressBack()
        }
    }
}