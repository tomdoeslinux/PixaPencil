package com.realtomjoney.pyxlmoose.activities.`1_main`

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import com.realtomjoney.pyxlmoose.R
import org.junit.Rule
import org.junit.Test
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.realtomjoney.pyxlmoose.AndroidTestUtilityFunctions
import com.realtomjoney.pyxlmoose.activities.main.MainActivity
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTestsWhenNewProjectIsCreated {
    @get:Rule
    var activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test fun uitest_fragmentNewCanvasProjectTitleTextInputEditText_isNotDisplayed_after_fragmentNewCanvasDoneButton_isPressed() {
        AndroidTestUtilityFunctions.createNewProject()
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputEditText)).check(ViewAssertions.doesNotExist())
    }

    @Test fun uitest_fragmentNewCanvasProjectTitleTextInputLayout_isNotDisplayed_after_fragmentNewCanvasDoneButton_isPressed() {
        AndroidTestUtilityFunctions.createNewProject()
        onView(withId(R.id.fragmentNewCanvas_projectTitleTextInputLayout)).check(ViewAssertions.doesNotExist())
    }

    @Test fun uitest_fragmentNewCanvasSpanCountTextInputEditText_isNotDisplayed_after_fragmentNewCanvasDoneButton_isPressed() {
        AndroidTestUtilityFunctions.createNewProject()
        onView(withId(R.id.fragmentNewCanvas_spanCountTextInputEditText)).check(ViewAssertions.doesNotExist())
    }

    @Test fun uitest_fragmentNewCanvasSpanCountTextInputLayout_isNotDisplayed_after_fragmentNewCanvasDoneButton_isPressed() {
        AndroidTestUtilityFunctions.createNewProject()
        onView(withId(R.id.fragmentNewCanvas_spanCountTextInputLayout)).check(ViewAssertions.doesNotExist())
    }

    @Test fun uitest_fragmentNewCanvasDoneButton_isNotDisplayed_after_fragmentNewCanvasDoneButton_isPressed() {
        AndroidTestUtilityFunctions.createNewProject()
        onView(withId(R.id.fragmentNewCanvas_doneButton)).check(ViewAssertions.doesNotExist())
    }
}
