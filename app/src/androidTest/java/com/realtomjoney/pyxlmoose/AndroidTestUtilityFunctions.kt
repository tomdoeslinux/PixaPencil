package com.realtomjoney.pyxlmoose

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*

object AndroidTestUtilityFunctions {
    private const val defaultProjectName = "Unnamed Project"
    private const val defaultProjectSpanCount = 5

    fun createNewProject(projectName: String = defaultProjectName, spanCount: Int = defaultProjectSpanCount) {
        onView(withId(R.id.activityMain_newProjectButton)).perform(click())
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).perform(replaceText(projectName))
        onView(withId(R.id.fragmentNewCanvas_spanCountTextInputEditText)).perform(replaceText(spanCount.toString()))
        onView(withId(R.id.fragmentNewCanvas_doneButton)).perform(click())
    }
}