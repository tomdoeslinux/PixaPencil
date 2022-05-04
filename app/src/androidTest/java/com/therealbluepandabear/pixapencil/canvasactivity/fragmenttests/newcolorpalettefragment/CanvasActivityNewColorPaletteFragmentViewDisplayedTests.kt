package com.therealbluepandabear.pixapencil.canvasactivity.fragmenttests.newcolorpalettefragment

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class CanvasActivityNewColorPaletteFragmentViewDisplayedTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(CanvasActivity::class.java)

    @Before
    fun setup() {
        onView(withId(R.id.activityCanvas_outerCanvasFragmentHost)).perform(ViewActions.click())
        Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().context)
        onView(ViewMatchers.withText(R.string.activityCanvasTopAppMenu_new_color_palette_str)).perform(ViewActions.click())
    }

    @Test
    fun checkColorPaletteNameTextInputEditText_IsDisplayed() {
        onView(withId(R.id.fragmentNewColorPalette_colorPaletteNameTextInputEditText)).check(matches(isDisplayed()))
    }

    @Test
    fun checkColorPaletteNameTextInputLayout_IsDisplayed() {
        onView(withId(R.id.fragmentNewColorPalette_colorPaletteNameTextInputLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkDoneButton_IsDisplayed() {
        onView(withId(R.id.fragmentNewColorPalette_doneButton)).check(matches(isDisplayed()))
    }

    @Test
    fun checkExtractFromCanvasCheckBox_IsDisplayed() {
        onView(withId(R.id.fragmentNewColorPalette_extractFromCanvasCheckBox)).check(matches(isDisplayed()))
    }

    @Test
    fun checkPrimaryFragmentHost_IsDisplayed() {
        onView(withId(R.id.fragmentNewColorPalette_primaryFragmentHost)).check(matches(isDisplayed()))
    }

    @Test
    fun checkColorPaletteRootLayout_IsDisplayed() {
        onView(withId(R.id.fragmentNewColorPalette_rootLayout)).check(matches(isDisplayed()))
    }
}
