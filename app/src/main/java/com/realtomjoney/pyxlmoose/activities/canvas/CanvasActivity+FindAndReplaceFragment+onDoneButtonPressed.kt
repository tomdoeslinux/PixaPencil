package com.realtomjoney.pyxlmoose.activities.canvas

import android.view.View
import com.realtomjoney.pyxlmoose.extensions.navigateHome

fun CanvasActivity.extendedOnDoneButtonPressed(colorToFind: Int?, colorToReplace: Int?) {
    showMenuItems()
    outerCanvasInstance.canvasFragment.myCanvasViewInstance.replacePixelsByColor(colorToFind!!, colorToReplace!!)

    navigateHome(supportFragmentManager, findAndReplaceFragmentInstance, binding.activityCanvasRootLayout, binding.activityCanvasPrimaryFragmentHost, intent.getStringExtra("PROJECT_TITLE")!!)
    setUpRecyclerView()

    if (isPrimaryColorSelected) {
        binding.activityCanvasColorPrimaryViewIndicator.visibility = View.VISIBLE
        binding.activityCanvasColorSecondaryViewIndicator.visibility = View.INVISIBLE
    } else {
        binding.activityCanvasColorPrimaryViewIndicator.visibility = View.INVISIBLE
        binding.activityCanvasColorSecondaryViewIndicator.visibility = View.VISIBLE
    }
}