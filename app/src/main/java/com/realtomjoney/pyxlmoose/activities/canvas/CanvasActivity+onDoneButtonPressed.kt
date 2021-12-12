package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.extensions.navigateHome
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun CanvasActivity.extendedOnDoneButtonPressed(selectedColor: Int) {
    currentFragmentInstance = null
    setPixelColor(selectedColor)
    navigateHome(supportFragmentManager, colorPickerFragmentInstance, binding.activityCanvasRootLayout, binding.activityCanvasPrimaryFragmentHost, StringConstants.APP_NAME)
}

fun CanvasActivity.extendedOnDoneButtonPressed(colorToFind: Int?, colorToReplace: Int?) {
    currentFragmentInstance = null

    val dataAsPixelList = canvasFragmentInstance.myCanvasViewInstance.saveData()

    for (pixel in dataAsPixelList) {
        if (pixel.pixelColor != null && pixel.pixelColor == colorToFind && colorToReplace != null)
            pixel.pixelColor = colorToReplace
    }

    canvasFragmentInstance.myCanvasViewInstance.drawFromPixelList(dataAsPixelList)

    navigateHome(supportFragmentManager, findAndReplaceFragmentInstance, binding.activityCanvasRootLayout, binding.activityCanvasPrimaryFragmentHost, StringConstants.APP_NAME)
}