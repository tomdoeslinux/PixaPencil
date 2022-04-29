package com.therealbluepandabear.pixapencil.mainactivity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
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
class MainActivityProjectCreationTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

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

}

