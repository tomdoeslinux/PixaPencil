package com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.models.Coordinates

fun CanvasActivity.circleToolOnPixelTapped(coordinatesTapped: Coordinates) {
    if (shapeOrigin == null) {
        shapeOrigin = coordinatesTapped
    } else {
        if (firstShapeDrawn) {
            clearPreviousShapePreview()
        }

        invisibleSquarePreviewAlgorithm.compute(shapeOrigin!!, coordinatesTapped)?.let {
            coordinates = it

            if (shapeOrigin!!.x > it.x) {
                circleAlgorithm.compute(it, shapeOrigin!!)
            } else {
                circleAlgorithm.compute(shapeOrigin!!, it)
            }
        }

        viewModel.currentBitmapAction?.actionData?.let {
            shapePreviewCache = it
        }

        if (!firstShapeDrawn) {
            firstShapeDrawn = true
        }
    }
}