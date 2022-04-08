package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.algorithms.LineAlgorithm
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.utility.BinaryPreviewStateSwitcher

var firstLineDrawn = false

fun lineToolOnPixelTapped(coordinatesTapped: Coordinates) {
    val lineAlgorithmInstance = LineAlgorithm(primaryAlgorithmInfoParameter)

    if (!lineMode_hasLetGo) {
        if (!first) {
            BinaryPreviewStateSwitcher.feedState(outerCanvasInstance.canvasFragment.pixelGridViewInstance.currentBitmapAction!!)
            BinaryPreviewStateSwitcher.switch()
        }
        BinaryPreviewStateSwitcher.feedState(outerCanvasInstance.canvasFragment.pixelGridViewInstance.currentBitmapAction!!)
        first = false

        if (firstLineDrawn) {
            outerCanvasInstance.canvasFragment.pixelGridViewInstance.currentBitmapAction!!.actionData.clear()
        }
    } else {
        outerCanvasInstance.canvasFragment.pixelGridViewInstance.currentBitmapAction = null
        lineMode_hasLetGo = false
        first = true
    }

    if (lineOrigin == null) {
        lineOrigin = coordinatesTapped
    } else {
        lineAlgorithmInstance.compute(lineOrigin!!, coordinatesTapped)
        firstLineDrawn = true
    }
}