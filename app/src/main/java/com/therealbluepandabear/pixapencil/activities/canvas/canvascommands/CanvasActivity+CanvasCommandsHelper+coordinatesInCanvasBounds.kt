package com.therealbluepandabear.pixapencil.activities.canvas.canvascommands

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.Coordinates

fun CanvasActivity.CanvasCommandsHelper.coordinatesInCanvasBounds(coordinates: Coordinates, currentTool: Tool, ignoreDither: Boolean = false): Boolean {
    return if (currentTool == Tool.DitherTool && !ignoreDither) {
        (coordinates.x in 0 until pixelGridViewInstance.canvasWidth && coordinates.y in 0 until pixelGridViewInstance.canvasHeight &&
                baseReference.viewModel.currentDitherBrush.algorithm.invoke(coordinates))
    } else {
        (coordinates.x in 0 until pixelGridViewInstance.canvasWidth && coordinates.y in 0 until pixelGridViewInstance.canvasHeight)
    }
}