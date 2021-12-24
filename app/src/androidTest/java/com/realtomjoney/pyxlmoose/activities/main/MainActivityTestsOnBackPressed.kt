package com.realtomjoney.pyxlmoose.activities.main

import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import org.junit.Rule
import org.junit.Test
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import junit.framework.Assert.assertTrue
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTestsOnBackPressed {
    @get:Rule
    var activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun uitest_activityMain_onBackPressed_lifecycleStateIsDestroyed() {
        Espresso.pressBackUnconditionally()
        assertTrue(activityTestRule.scenario.state == Lifecycle.State.DESTROYED)
    }
}
