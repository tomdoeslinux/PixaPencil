package com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.models.Coordinates

fun CanvasActivity.ellipseToolOnPixelTapped(coordinatesTapped: Coordinates) {
    if (shapeOrigin == null) {
        shapeOrigin = coordinatesTapped
    } else {
        if (firstShapeDrawn) {
            clearPreviousShapePreview()
        }

        val coordinates = invisibleRectanglePreviewAlgorithm.compute(shapeOrigin!!, coordinatesTapped)
        this.coordinates = coordinates

        if (
            shapeOrigin!!.y == coordinates.y ||
            shapeOrigin!!.y + 1 == coordinatesTapped.y ||
            shapeOrigin!!.y - 1 == coordinatesTapped.y ) {

            visibleRectanglePreviewAlgorithm.compute(shapeOrigin!!, coordinatesTapped)
        } else {
            if (shapeOrigin!!.x > coordinates.x) {
                ellipseAlgorithm.compute(coordinates, shapeOrigin!!)
            } else {
                ellipseAlgorithm.compute(shapeOrigin!!, coordinates)
            }

            firstShapeDrawn = true
        }

        viewModel.currentBitmapAction?.actionData?.let {
            shapePreviewCache = it
        }

        if (!firstShapeDrawn) {
            firstShapeDrawn = true
        }
    }
}