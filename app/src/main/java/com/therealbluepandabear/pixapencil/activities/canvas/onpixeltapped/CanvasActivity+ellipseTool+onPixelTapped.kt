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

    if (shapeOrigin == null) {
        shapeOrigin = coordinatesTapped
    } else {
        coordinates = coordinatesTapped

        rectanglePreviewAlgorithmInstance.compute(shapeOrigin!!, coordinatesTapped)

        if (shapeOrigin!!.y == coordinates!!.y || shapeOrigin!!.y + 1 == coordinatesTapped.y || shapeOrigin!!.y - 1 == coordinatesTapped.y ) {
            val ra = RectanglePreviewAlgorithm(primaryAlgorithmInfoParameter)
            ra.compute(shapeOrigin!!, coordinatesTapped)
        } else {
            if (shapeOrigin!!.x > coordinates!!.x) {
                ellipseAlgorithmInstance.compute(coordinates!!, shapeOrigin!!)
            } else {
                ellipseAlgorithmInstance.compute(shapeOrigin!!, coordinates!!)
            }

            firstEllipseDrawn = true
        }
    }
}