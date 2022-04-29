package com.therealbluepandabear.pixapencil

import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
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

    private val defaultProjectName = "Project"

    @Test
    fun create10x10() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
        onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText("10"))
        onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText("10"))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
    }

    @Test
    fun create25x25() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
        onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText("25"))
        onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText("25"))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
    }

    @Test
    fun create50x50() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
        onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText("50"))
        onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText("50"))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
    }

    @Test
    fun create100x100() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
        onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText("100"))
        onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText("100"))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
    }

    @Test
    fun create250x250() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
        onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText("250"))
        onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText("250"))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
    }

    @Test
    fun create500x500() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
        onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText("500"))
        onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText("500"))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
    }

    @Test
    fun create1000x1000() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
        onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText("1000"))
        onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText("1000"))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
    }

    @Test
    fun create10x5() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
        onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText("10"))
        onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText("5"))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
    }

    @Test
    fun create50x10() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
        onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText("50"))
        onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText("10"))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
    }

    @Test
    fun create100x50() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
        onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText("100"))
        onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText("50"))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
    }

    @Test
    fun create200x30() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
        onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText("200"))
        onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText("30"))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
    }

    @Test
    fun create550x250() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
        onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText("550"))
        onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText("250"))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
    }

    @Test
    fun create1500x500() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
        onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText("1500"))
        onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText("500"))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
    }

    @Test
    fun create1000x100() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
        onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText("1000"))
        onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText("100"))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
    }

    @Test
    fun create5x10() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
        onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText("5"))
        onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText("10"))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
    }

    @Test
    fun create10x50() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
        onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText("10"))
        onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText("50"))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
    }

    @Test
    fun create50x100() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
        onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText("50"))
        onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText("100"))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
    }

    @Test
    fun create30x200() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
        onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText("30"))
        onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText("200"))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
    }

    @Test
    fun create250x550() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
        onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText("250"))
        onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText("550"))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
    }

    @Test
    fun create500x1500() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
        onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText("500"))
        onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText("1500"))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
    }

    @Test
    fun create100x1000() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
        onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText("100"))
        onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText("1000"))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
    }

    @Test
    fun clickOnNewProjectButtonThenCheckForEmptyNameError() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText("500"))
        onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText("1500"))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputLayout)).check(matches(hasDescendant(withText(R.string.exception_invalid_project_name_in_code_str))))
    }

    @Test
    fun clickOnNewProjectButtonThenCheckForEmptyWidthError() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
        onView(withId(R.id.fragmentNewCanvas_heightTextInputEditText)).perform(typeText("1500"))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_widthTextInputLayout)).check(matches(hasDescendant(withText(R.string.exception_empty_width_in_code_str))))
    }

    @Test
    fun clickOnNewProjectButtonThenCheckForEmptyHeightError() {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(typeText(defaultProjectName))
        onView(withId(R.id.fragmentNewCanvas_widthTextInputEditText)).perform(typeText("500"))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_heightTextInputLayout)).check(matches(hasDescendant(withText(R.string.exception_empty_height_in_code_str))))
    }
}