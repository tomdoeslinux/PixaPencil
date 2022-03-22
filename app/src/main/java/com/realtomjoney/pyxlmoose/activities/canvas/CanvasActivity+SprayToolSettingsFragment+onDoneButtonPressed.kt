package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.extensions.navigateHome
import com.realtomjoney.pyxlmoose.utility.IntConstants
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun CanvasActivity.extendedOnDoneButtonPressed(radius: String, strength: String) {
    navigateHome(supportFragmentManager, currentFragmentInstance!!, binding.activityCanvasRootLayout, binding.activityCanvasPrimaryFragmentHost, intent.getStringExtra(
        StringConstants.ProjectTitleExtra)!!)
    currentFragmentInstance = null
    showMenuItems()

    switchSelectedColorIndicator()

    IntConstants.SprayRadius = radius.toInt()
    IntConstants.SprayStrength = strength.toInt()
}