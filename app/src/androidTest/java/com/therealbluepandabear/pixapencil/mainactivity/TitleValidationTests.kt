package com.therealbluepandabear.pixapencil.mainactivity

import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class TitleValidationTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val appTitle = "PixaPencil"
    private val newProjectTitle = "New Project"

    private val actionBarIdentifier = "action_bar"

    @Test
    fun checkTitleIsPixaPencil() {
        onView(allOf(instanceOf(TextView::class.java), withParent(withResourceName(actionBarIdentifier))))
            .check(matches(withText(appTitle)))
    }

    @Test
    fun checkTitleIsNewProjectWhenClickingOnNewProjectButton() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())

        onView(allOf(instanceOf(TextView::class.java), withParent(withResourceName(actionBarIdentifier))))
            .check(matches(withText(newProjectTitle)))
    }

    @Test
    fun checkTitleSwitchesBackToPixaPencilWhenPressingBackFromNewProjectFragment() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        pressBack()
        onView(allOf(instanceOf(TextView::class.java), withParent(withResourceName(actionBarIdentifier))))
            .check(matches(withText(appTitle)))
    }
}