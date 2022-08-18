package com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.models.Coordinates

fun CanvasActivity.rectangleToolOnPixelTapped(coordinatesTapped: Coordinates) {
    if (shapeOrigin == null) {
        shapeOrigin = coordinatesTapped
    } else {
        if (firstShapeDrawn) {
            clearPreviousShapePreview()
        }

        this.coordinates = if (
            viewModel.currentTool == Tool.RectangleTool ||
            viewModel.currentTool == Tool.OutlinedRectangleTool) {

            visibleRectanglePreviewAlgorithm.compute(shapeOrigin!!, coordinatesTapped)
        } else {
            visibleSquarePreviewAlgorithm.compute(shapeOrigin!!, coordinatesTapped)
        }

        viewModel.currentBitmapAction?.actionData?.let {
            shapePreviewCache = it
        }

        if (!firstShapeDrawn) {
            firstShapeDrawn = true
        }
    }
}