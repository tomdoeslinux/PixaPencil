package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.extensions.navigateHome

fun CanvasActivity.extendedOnDoneButtonPressed(colorToFind: Int?, colorToReplace: Int?) {
    canvasStates.add(canvasFragmentInstance.myCanvasViewInstance.saveData())

    currentFragmentInstance = null

    val dataAsPixelList = canvasFragmentInstance.myCanvasViewInstance.saveData()

    for (pixel in dataAsPixelList) {
        if (pixel.pixelColor != null && pixel.pixelColor == colorToFind && colorToReplace != null) pixel.pixelColor = colorToReplace
    }

    canvasFragmentInstance.myCanvasViewInstance.drawFromPixelList(dataAsPixelList)

    navigateHome(supportFragmentManager, findAndReplaceFragmentInstance, binding.activityCanvasRootLayout, binding.activityCanvasPrimaryFragmentHost, intent.getStringExtra("PROJECT_TITLE")!!)

    extendedSetUpRecyclerView()
}