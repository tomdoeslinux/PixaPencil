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
class MainActivityNavigationTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    // Last successful test completion: 04-30 17:39 (API 32)
    // Last successful test completion on API 32: 04-30 17:39
    @Test
    fun clickOnNewProjectButtonThenClickOnProjectNameTextInputLayoutThenGoBack() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputLayout)).perform(click())
        pressBack()
    }

    // Last successful test completion: 04-30 17:45 (API 32)
    // Last successful test completion on API 32: 04-30 17:45
    @Test
    fun clickOnNewProjectButtonThenClickOnProjectNameTextInputLayoutThenGoBackTwice() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputLayout)).perform(click())
        pressBack()
        pressBack()
    }

    // Last successful test completion: 04-30 17:40 (API 32)
    // Last successful test completion on API 32: 04-30 17:40
    @Test
    fun clickOnNewProjectButtonThenClickOnProjectNameTextInputLayoutThenGoBackTwice25Times() {
        for (i in 0..25) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputLayout)).perform(click())
            pressBack()
            pressBack()
        }
    }

    // Last successful test completion: 04-30 17:52 (API 32)
    // Last successful test completion on API 32: 04-30 17:52
    @Test
    fun clickOnNewProjectButtonThenClickOnProjectNameTextInputLayoutThenGoBackTwice50Times() {
        for (i in 0..50) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputLayout)).perform(click())
            pressBack()
            pressBack()
        }
    }

    // Last successful test completion: 04-30 17:39 (API 32)
    // Last successful test completion on API 32: 04-30 17:39
    @Test
    fun clickOnNewProjectButtonThenClickOnProjectNameTextInputLayoutThenGoBackTwice100Times() {
        for (i in 0..100) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputLayout)).perform(click())
            pressBack()
            pressBack()
        }
    }

    // Last successful test completion: 04-30 17:39 (API 32)
    // Last successful test completion on API 32: 04-30 17:39
    @Test
    fun clickOnNewProjectButtonThenClickOnProjectWidthTextInputLayoutThenGoBack() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_widthTextInputLayout)).perform(click())
        pressBack()
    }

    // Last successful test completion: 04-30 17:46 (API 32)
    // Last successful test completion on API 32: 04-30 17:46
    @Test
    fun clickOnNewProjectButtonThenClickOnProjectWidthTextInputLayoutThenGoBackTwice() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_widthTextInputLayout)).perform(click())
        pressBack()
        pressBack()
    }

    // Last successful test completion: 04-30 17:51 (API 32)
    // Last successful test completion on API 32: 04-30 17:51
    @Test
    fun clickOnNewProjectButtonThenClickOnProjectWidthTextInputLayoutThenGoBackTwice25Times() {
        for (i in 0..25) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_widthTextInputLayout)).perform(click())
            pressBack()
            pressBack()
        }
    }

    // Last successful test completion: 04-30 17:41 (API 32)
    // Last successful test completion on API 32: 04-30 17:41
    @Test
    fun clickOnNewProjectButtonThenClickOnProjectWidthTextInputLayoutThenGoBackTwice50Times() {
        for (i in 0..50) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_widthTextInputLayout)).perform(click())
            pressBack()
            pressBack()
        }
    }

    // Last successful test completion: 04-30 17:48 (API 32)
    // Last successful test completion on API 32: 04-30 17:48
    @Test
    fun clickOnNewProjectButtonThenClickOnProjectWidthTextInputLayoutThenGoBackTwice100Times() {
        for (i in 0..100) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_widthTextInputLayout)).perform(click())
            pressBack()
            pressBack()
        }
    }

    // Last successful test completion: 04-30 17:45 (API 32)
    // Last successful test completion on API 32: 04-30 17:45
    @Test
    fun clickOnNewProjectButtonThenClickOnProjectHeightTextInputLayoutThenGoBack() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_heightTextInputLayout)).perform(click())
        pressBack()
    }

    // Last successful test completion: 04-30 17:53 (API 32)
    // Last successful test completion on API 32: 04-30 17:53
    @Test
    fun clickOnNewProjectButtonThenClickOnProjectHeightTextInputLayoutThenGoBackTwice() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_heightTextInputLayout)).perform(click())
        pressBack()
        pressBack()
    }

    // Last successful test completion: 04-30 17:40 (API 32)
    // Last successful test completion on API 32: 04-30 17:40
    @Test
    fun clickOnNewProjectButtonThenClickOnProjectHeightTextInputLayoutThenGoBackTwice25Times() {
        for (i in 0..25) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_heightTextInputLayout)).perform(click())
            pressBack()
            pressBack()
        }
    }

    // Last successful test completion: 04-30 17:53 (API 32)
    // Last successful test completion on API 32: 04-30 17:53
    @Test
    fun clickOnNewProjectButtonThenClickOnProjectHeightTextInputLayoutThenGoBackTwice50Times() {
        for (i in 0..50) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_heightTextInputLayout)).perform(click())
            pressBack()
            pressBack()
        }
    }

    // Last successful test completion: 04-30 17:50 (API 32)
    // Last successful test completion on API 32: 04-30 17:50
    @Test
    fun clickOnNewProjectButtonThenClickOnProjectHeightTextInputLayoutThenGoBackTwice100Times() {
        for (i in 0..100) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_heightTextInputLayout)).perform(click())
            pressBack()
            pressBack()
        }
    }

    // Last successful test completion: 04-30 17:39 (API 32)
    // Last successful test completion on API 32: 04-30 17:39
    @Test
    fun clickOnNewProjectButtonThenClickOnAllTextInputLayoutsThenGoBack() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputLayout)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_widthTextInputLayout)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_heightTextInputLayout)).perform(click())
        pressBack()
    }

    // Last successful test completion: 04-30 17:39 (API 32)
    // Last successful test completion on API 32: 04-30 17:39
    @Test
    fun clickOnNewProjectButtonThenClickOnAllTextInputLayoutsThenGoBackTwice() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputLayout)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_widthTextInputLayout)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_heightTextInputLayout)).perform(click())
        pressBack()
        pressBack()
    }

    // Last successful test completion: 04-30 17:37 (API 32)
    // Last successful test completion on API 32: 04-30 17:37
    @Test
    fun clickOnNewProjectButtonThenClickOnAllTextInputLayoutsThenGoBackTwice25Times() {
        for (i in 0..25) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputLayout)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_widthTextInputLayout)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_heightTextInputLayout)).perform(click())
            pressBack()
            pressBack()
        }
    }

    // Last successful test completion: 04-30 17:46 (API 32)
    // Last successful test completion on API 32: 04-30 17:46
    @Test
    fun clickOnNewProjectButtonThenClickOnAllTextInputLayoutsThenGoBackTwice50Times() {
        for (i in 0..50) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputLayout)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_widthTextInputLayout)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_heightTextInputLayout)).perform(click())
            pressBack()
            pressBack()
        }
    }

    // Last successful test completion: 04-30 17:44 (API 32)
    // Last successful test completion on API 32: 04-30 17:44
    @Test
    fun clickOnNewProjectButtonThenClickOnAllTextInputLayoutsThenGoBackTwice100Times() {
        for (i in 0..100) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputLayout)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_widthTextInputLayout)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_heightTextInputLayout)).perform(click())
            pressBack()
            pressBack()
        }
    }
}