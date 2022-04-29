package com.therealbluepandabear.pixapencil

import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityEspressoTest {
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
        for (i in 0..50) {
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
        for (i in 0..50) {
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
    fun clickOnNewProjectButton() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
    }

    @Test
    fun clickOnNewProjectButton25Times() {
        for (i in 0..25) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            pressBack()
        }
    }

    @Test
    fun clickOnNewProjectButton50Times() {
        for (i in 0..50) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            pressBack()
        }
    }

    @Test
    fun clickOnNewProjectButton100Times() {
        for (i in 0..100) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            pressBack()
        }
    }

    @Test
    fun clickOnCommunityItem() {
        onView(withId(R.id.activityMainTopAppMenu_community_item)).perform(click())
    }

    @Test
    fun clickOnCommunityItem25Times() {
        for (i in 0..25) {
            onView(withId(R.id.activityMainTopAppMenu_community_item)).perform(click())
            pressBack()
        }
    }

    @Test
    fun clickOnCommunityItem50Times() {
        for (i in 0..50) {
            onView(withId(R.id.activityMainTopAppMenu_community_item)).perform(click())
            pressBack()
        }
    }

    @Test
    fun clickOnCommunityItem100Times() {
        for (i in 0..100) {
            onView(withId(R.id.activityMainTopAppMenu_community_item)).perform(click())
            pressBack()
        }
    }

    @Test
    fun checkTitleIsPixaPencil() {
        onView(allOf(instanceOf(
            TextView::class.java),
            withParent(withResourceName("action_bar")))
        ).check(matches(withText("PixaPencil")))
    }

    @Test
    fun checkTitleIsNewProjectWhenClickingOnNewProjectButton() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())

        onView(allOf(instanceOf(
            TextView::class.java),
            withParent(withResourceName("action_bar")))
        ).check(matches(withText("New Project")))
    }

    @Test
    fun checkTitleSwitchesBackToPixaPencilWhenPressingBackFromNewProjectFragment() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        pressBack()

        onView(allOf(instanceOf(
            TextView::class.java),
            withParent(withResourceName("action_bar")))
        ).check(matches(withText("PixaPencil")))
    }

    @Test
    fun clickOnNewProjectButtonThenClickOnProjectNameTextInputLayoutThenGoBack() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputLayout)).perform(click())
        pressBack()
    }

    @Test
    fun clickOnNewProjectButtonThenClickOnProjectNameTextInputLayoutThenGoBackTwice() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputLayout)).perform(click())
        pressBack()
        pressBack()
    }

    @Test
    fun clickOnNewProjectButtonThenClickOnProjectNameTextInputLayoutThenGoBackTwice25Times() {
        for (i in 0..25) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputLayout)).perform(click())
            pressBack()
            pressBack()
        }
    }

    @Test
    fun clickOnNewProjectButtonThenClickOnProjectNameTextInputLayoutThenGoBackTwice50Times() {
        for (i in 0..50) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputLayout)).perform(click())
            pressBack()
            pressBack()
        }
    }

    @Test
    fun clickOnNewProjectButtonThenClickOnProjectNameTextInputLayoutThenGoBackTwice100Times() {
        for (i in 0..100) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputLayout)).perform(click())
            pressBack()
            pressBack()
        }
    }

    @Test
    fun clickOnNewProjectButtonThenClickOnProjectWidthTextInputLayoutThenGoBack() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_widthTextInputLayout)).perform(click())
        pressBack()
    }

    @Test
    fun clickOnNewProjectButtonThenClickOnProjectWidthTextInputLayoutThenGoBackTwice() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_widthTextInputLayout)).perform(click())
        pressBack()
        pressBack()
    }

    @Test
    fun clickOnNewProjectButtonThenClickOnProjectWidthTextInputLayoutThenGoBackTwice25Times() {
        for (i in 0..25) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_widthTextInputLayout)).perform(click())
            pressBack()
            pressBack()
        }
    }

    @Test
    fun clickOnNewProjectButtonThenClickOnProjectWidthTextInputLayoutThenGoBackTwice50Times() {
        for (i in 0..50) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_widthTextInputLayout)).perform(click())
            pressBack()
            pressBack()
        }
    }

    @Test
    fun clickOnNewProjectButtonThenClickOnProjectWidthTextInputLayoutThenGoBackTwice100Times() {
        for (i in 0..100) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_widthTextInputLayout)).perform(click())
            pressBack()
            pressBack()
        }
    }

    @Test
    fun clickOnNewProjectButtonThenClickOnProjectHeightTextInputLayoutThenGoBack() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_heightTextInputLayout)).perform(click())
        pressBack()
    }

    @Test
    fun clickOnNewProjectButtonThenClickOnProjectHeightTextInputLayoutThenGoBackTwice() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_heightTextInputLayout)).perform(click())
        pressBack()
        pressBack()
    }

    @Test
    fun clickOnNewProjectButtonThenClickOnProjectHeightTextInputLayoutThenGoBackTwice25Times() {
        for (i in 0..25) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_heightTextInputLayout)).perform(click())
            pressBack()
            pressBack()
        }
    }

    @Test
    fun clickOnNewProjectButtonThenClickOnProjectHeightTextInputLayoutThenGoBackTwice50Times() {
        for (i in 0..50) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_heightTextInputLayout)).perform(click())
            pressBack()
            pressBack()
        }
    }

    @Test
    fun clickOnNewProjectButtonThenClickOnProjectHeightTextInputLayoutThenGoBackTwice100Times() {
        for (i in 0..100) {
            onView(withId(R.id.activityMain_newProjectButton)).perform(click())
            onView(withId(R.id.fragmentNewCanvas_heightTextInputLayout)).perform(click())
            pressBack()
            pressBack()
        }
    }

    @Test
    fun clickOnNewProjectButtonThenClickOnAllTextInputLayoutsThenGoBack() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputLayout)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_widthTextInputLayout)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_heightTextInputLayout)).perform(click())
        pressBack()
    }

    @Test
    fun clickOnNewProjectButtonThenClickOnAllTextInputLayoutsThenGoBackTwice() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputLayout)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_widthTextInputLayout)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_heightTextInputLayout)).perform(click())
        pressBack()
        pressBack()
    }

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