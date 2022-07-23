package com.therealbluepandabear.pixapencil.canvasactivity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class CanvasActivityTopAppMenuTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(CanvasActivity::class.java)

    @Before
    fun setup() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().context)
    }

    @Test
    fun checkClearCanvasItemIsDisplayed() {
        onView(withText(R.string.activityCanvasTopAppMenu_clear_canvas)).check(matches(isDisplayed()))
    }


    @Test
    fun checkNewColorPaletteItemIsDisplayed() {
        onView(withText(R.string.activityCanvasTopAppMenu_new_color_palette)).check(matches(isDisplayed()))
    }

    @Test
    fun checkGridItemIsDisplayed() {
        onView(withText(R.string.activityCanvasTopAppMenu_grid)).check(matches(isDisplayed()))
    }

    @Test
    fun checkPixelPerfectItemIsDisplayed() {
        onView(withText(R.string.activityCanvasTopAppMenu_pixel_perfect)).check(matches(isDisplayed()))
    }

    @Test
    fun checkResetZoomItemIsDisplayed() {
        onView(withText(R.string.activityCanvasTopAppMenu_reset_zoom)).check(matches(isDisplayed()))
    }

    @Test
    fun checkSymmetryItemIsDisplayed() {
        onView(withText(R.string.activityCanvasTopAppMenu_symmetry)).check(matches(isDisplayed()))
    }

    @Test
    fun checkSaveToPNGItemIsDisplayed() {
        onView(withText(R.string.activityCanvasTopAppMenu_save_as_png)).check(matches(isDisplayed()))
    }

    @Test
    fun checkSaveToJPGItemIsDisplayed() {
        onView(withText(R.string.activityCanvasTopAppMenu_save_as_jpg)).check(matches(isDisplayed()))
    }

    @Test
    fun checkRotate90DegreesClockwiseSubItemDoesNotExist() {
        onView(withText(R.string.activityCanvasTopAppMenu_rotate_90_clockwise)).check(doesNotExist())
    }

    @Test
    fun checkRotate90DegreesAntiClockwiseSubItemDoesNotExist() {
        onView(withText(R.string.activityCanvasTopAppMenu_rotate_90_anti_clockwise)).check(doesNotExist())
    }

    @Test
    fun checkRotate180DegreesSubItemDoesNotExist() {
        onView(withText(R.string.activityCanvasTopAppMenu_rotate_180)).check(doesNotExist())
    }

    @Test
    fun checkHorizontalSymmetrySubItemDoesNotExist() {
        onView(withText(R.string.activityCanvasTopAppMenu_symmetry_horizontal)).check(doesNotExist())
    }

    @Test
    fun checkVerticalSymmetrySubItemDoesNotExist() {
        onView(withText(R.string.activityCanvasTopAppMenu_symmetry_vertical)).check(doesNotExist())
    }

    @Test
    fun checkQuadSymmetrySubItemDoesNotExist() {
        onView(withText(R.string.activityCanvasTopAppMenu_symmetry_quad)).check(doesNotExist())
    }

    @Test
    fun checkOctalSymmetrySubItemDoesNotExist() {
        onView(withText(R.string.activityCanvasTopAppMenu_symmetry_octal)).check(doesNotExist())
    }

    @Test
    fun checkRotate90DegreesClockwiseSubItemIsDisplayedWhenRotateItemClicked() {
        onView(withText(R.string.activityCanvasTopAppMenu_rotate)).perform(click())
        onView(withText(R.string.activityCanvasTopAppMenu_rotate_90_clockwise)).check(matches(isDisplayed()))
    }

    @Test
    fun checkRotate90DegreesAntiClockwiseSubItemIsDisplayedWhenRotateItemClicked() {
        onView(withText(R.string.activityCanvasTopAppMenu_rotate)).perform(click())
        onView(withText(R.string.activityCanvasTopAppMenu_rotate_90_anti_clockwise)).check(matches(isDisplayed()))
    }

    @Test
    fun checkRotate180DegreesSubItemIsDisplayedWhenRotateItemClicked() {
        onView(withText(R.string.activityCanvasTopAppMenu_rotate)).perform(click())
        onView(withText(R.string.activityCanvasTopAppMenu_rotate_180)).check(matches(isDisplayed()))
    }

    @Test
    fun checkHorizontalSymmetrySubItemIsDisplayedWhenSymmetryItemClicked() {
        onView(withText(R.string.activityCanvasTopAppMenu_symmetry)).perform(click())
        onView(withText(R.string.activityCanvasTopAppMenu_symmetry_horizontal)).check(matches(isDisplayed()))
    }

    @Test
    fun checkVerticalSymmetrySubItemIsDisplayedWhenSymmetryItemClicked() {
        onView(withText(R.string.activityCanvasTopAppMenu_symmetry)).perform(click())
        onView(withText(R.string.activityCanvasTopAppMenu_symmetry_vertical)).check(matches(isDisplayed()))
    }

    @Test
    fun checkQuadSymmetrySubItemIsDisplayedWhenSymmetryItemClicked() {
        onView(withText(R.string.activityCanvasTopAppMenu_symmetry)).perform(click())
        onView(withText(R.string.activityCanvasTopAppMenu_symmetry_quad)).check(matches(isDisplayed()))
    }

    @Test
    fun checkOctalSymmetrySubItemIsDisplayedWhenSymmetryItemClicked() {
        onView(withText(R.string.activityCanvasTopAppMenu_symmetry)).perform(click())
        onView(withText(R.string.activityCanvasTopAppMenu_symmetry_octal)).check(matches(isDisplayed()))
    }
}
