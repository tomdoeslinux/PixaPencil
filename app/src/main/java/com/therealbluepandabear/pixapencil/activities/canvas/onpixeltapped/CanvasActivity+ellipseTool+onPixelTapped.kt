package com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.algorithms.EllipseAlgorithm
import com.therealbluepandabear.pixapencil.algorithms.RectanglePreviewAlgorithm
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.utility.BinaryPreviewStateSwitcher

var firstEllipseDrawn = false

fun CanvasActivity.ellipseToolOnPixelTapped(coordinatesTapped: Coordinates) {
    val rectanglePreviewAlgorithmInstance = RectanglePreviewAlgorithm(primaryAlgorithmInfoParameter, true)
    val ellipseAlgorithmInstance = EllipseAlgorithm(primaryAlgorithmInfoParameter)

    if (!ellipseModeHasLetGo) {
        if (!first) {
            BinaryPreviewStateSwitcher.feedState(viewModel.currentBitmapAction!!)
            BinaryPreviewStateSwitcher.switch()
        }
        BinaryPreviewStateSwitcher.feedState(viewModel.currentBitmapAction!!)
        first = false

        if (firstEllipseDrawn) {
            viewModel.currentBitmapAction!!.actionData.clear()
        }
    } else {
        viewModel.currentBitmapAction = null
        ellipseModeHasLetGo = false
        first = true
    }

    if (ellipseOrigin == null) {
        ellipseOrigin = coordinatesTapped
    } else {
        coordinates = coordinatesTapped

        rectanglePreviewAlgorithmInstance.compute(ellipseOrigin!!, coordinatesTapped)

        if (ellipseOrigin!!.y == coordinates!!.y || ellipseOrigin!!.y + 1 == coordinatesTapped.y || ellipseOrigin!!.y - 1 == coordinatesTapped.y ) {
            val ra = RectanglePreviewAlgorithm(primaryAlgorithmInfoParameter)
            ra.compute(ellipseOrigin!!, coordinatesTapped)
        } else {
            if (ellipseOrigin!!.x > coordinates!!.x) {
                ellipseAlgorithmInstance.compute(coordinates!!, ellipseOrigin!!)
            } else {
                ellipseAlgorithmInstance.compute(ellipseOrigin!!, coordinates!!)
            }

            firstEllipseDrawn = true
        }
    }
}