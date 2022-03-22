package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.extensions.navigateHome
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun CanvasActivity.extendedOnDoneButtonPressed(colorToFind: Int?, colorToReplace: Int?) {
    showMenuItems()
    outerCanvasInstance.canvasFragment.myCanvasViewInstance.replacePixelsByColor(colorToFind!!, colorToReplace!!)

    navigateHome(supportFragmentManager, findAndReplaceFragmentInstance, binding.activityCanvasRootLayout, binding.activityCanvasPrimaryFragmentHost, intent.getStringExtra(StringConstants.ProjectTitleExtra)!!)
    setUpRecyclerView()
    switchSelectedColorIndicator()
}