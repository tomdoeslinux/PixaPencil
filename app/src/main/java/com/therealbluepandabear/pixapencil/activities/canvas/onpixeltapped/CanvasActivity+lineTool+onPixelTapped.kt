package com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped

import com.therealbluepandabear.pixapencil.activities.canvas.lineMode_hasLetGo
import com.therealbluepandabear.pixapencil.activities.canvas.primaryAlgorithmInfoParameter
import com.therealbluepandabear.pixapencil.algorithms.LineAlgorithm
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.utility.BinaryPreviewStateSwitcher

var firstLineDrawn = false

fun lineToolOnPixelTapped(coordinatesTapped: Coordinates) {
    val lineAlgorithmInstance = LineAlgorithm(primaryAlgorithmInfoParameter)

    if (!lineMode_hasLetGo) {
        if (!first) {
            BinaryPreviewStateSwitcher.feedState(pixelGridViewInstance.currentBitmapAction!!)
            BinaryPreviewStateSwitcher.switch()
        }
        BinaryPreviewStateSwitcher.feedState(pixelGridViewInstance.currentBitmapAction!!)
        first = false

        if (firstLineDrawn) {
            pixelGridViewInstance.currentBitmapAction!!.actionData.clear()
        }
    } else {
        pixelGridViewInstance.currentBitmapAction = null
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