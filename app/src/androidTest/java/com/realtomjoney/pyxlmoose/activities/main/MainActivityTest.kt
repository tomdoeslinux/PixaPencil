package com.realtomjoney.pyxlmoose.activities.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.realtomjoney.pyxlmoose.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist


@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun uitest_activityMainFAB_isDisplayed() {
        onView(withId(R.id.floatingActionButton))
            .check(matches(isDisplayed()))
    }

    @Test
    fun uitest_fragmentNewCanvasRootLayout_isDisplayed() {
        onView(withId(R.id.floatingActionButton))
            .perform(click())
        onView(withId(R.id.fragmentNewCanvas_rootLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun uitest_fragmentNewCanvasRootLayout_isNotDisplayed() {
        onView(withId(R.id.fragmentNewCanvas_rootLayout)).check(doesNotExist())
    }

    @Test
    fun uitest_fragmentNewCanvasProjectTitleTextInputEditText_isDisplayed() {
        onView(withId(R.id.floatingActionButton))
            .perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).check(matches(isDisplayed()))
    }

    @Test
    fun uitest_recentCreationsRecyclerView_isDisplayed() {
        onView(withId(R.id.recentCreationsRecyclerView))
            .check(matches(isDisplayed()))
    }
}
