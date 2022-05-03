package com.therealbluepandabear.pixapencil.mainactivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityNewProjectFragmentViewDisplayedTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setup() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
    }

    @Test
    fun checkProjectTitleTextInputLayoutIsDisplayed() {
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkProjectTitleTextInputEditTextIsDisplayed() {
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).check(matches(isDisplayed()))
    }

    @Test
    fun checkWidthTextInputLayoutIsDisplayed() {
        onView(withId(R.id.fragmentNewCanvas_widthTextInputLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkWidthTextInputEditTextIsDisplayed() {
        onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).check(matches(isDisplayed()))
    }

    @Test
    fun checkHeightTextInputLayoutIsDisplayed() {
        onView(withId(R.id.fragmentNewCanvas_heightTextInputLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkHeightTextInputEditTextIsDisplayed() {
        onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).check(matches(isDisplayed()))
    }
}