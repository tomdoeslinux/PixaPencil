package com.realtomjoney.pyxlmoose.activities.canvas

import android.view.View
import com.realtomjoney.pyxlmoose.extensions.navigateHome
import com.realtomjoney.pyxlmoose.utility.IntConstants

fun CanvasActivity.extendedOnDoneButtonPressed(radius: String, strength: String) {
    navigateHome(supportFragmentManager, currentFragmentInstance!!, binding.activityCanvasRootLayout, binding.activityCanvasPrimaryFragmentHost, intent.getStringExtra("PROJECT_TITLE")!!)
    currentFragmentInstance = null
    showMenuItems()

    if (isPrimaryColorSelected) {
        binding.activityCanvasColorPrimaryViewIndicator.visibility = View.VISIBLE
        binding.activityCanvasColorSecondaryViewIndicator.visibility = View.INVISIBLE
    } else {
        binding.activityCanvasColorPrimaryViewIndicator.visibility = View.INVISIBLE
        binding.activityCanvasColorSecondaryViewIndicator.visibility = View.VISIBLE
    }


    IntConstants.RADIUS = radius.toInt()
    IntConstants.STRENGTH = strength.toInt()
}