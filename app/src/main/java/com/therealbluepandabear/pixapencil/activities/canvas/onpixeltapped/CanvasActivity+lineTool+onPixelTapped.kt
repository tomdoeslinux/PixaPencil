package com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.models.Coordinates

fun CanvasActivity.lineToolOnPixelTapped(coordinatesTapped: Coordinates) {
    if (shapeOrigin == null) {
        shapeOrigin = coordinatesTapped
    } else {
        if (firstShapeDrawn) {
            clearPreviousShapePreview()
        }

        lineAlgorithm.compute(shapeOrigin!!, coordinatesTapped)

        viewModel.currentBitmapAction?.actionData?.let {
            shapePreviewCache = it
        }

        if (!firstShapeDrawn) {
            firstShapeDrawn = true
        }
    }
}