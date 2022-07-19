package com.therealbluepandabear.pixapencil.activities.canvas.canvascommands

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.models.Coordinates

fun CanvasActivity.CanvasCommandsHelper.coordinatesInCanvasBounds(coordinates: Coordinates, currentTool: Tool, ignoreDither: Boolean = false): Boolean {
    return if (currentTool == Tool.DitherTool && !ignoreDither) {
        (coordinates.x in 0 until baseReference.binding.activityCanvasPixelGridView.pixelGridViewBitmap.width && coordinates.y in 0 until baseReference.binding.activityCanvasPixelGridView.pixelGridViewBitmap.height &&
                baseReference.viewModel.currentDitherBrush.algorithm.invoke(coordinates))
    } else {
        (coordinates.x in 0 until  baseReference.binding.activityCanvasPixelGridView.pixelGridViewBitmap.width && coordinates.y in 0 until  baseReference.binding.activityCanvasPixelGridView.pixelGridViewBitmap.height)
    }
}