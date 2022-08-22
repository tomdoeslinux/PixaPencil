package com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.coordinatesInCanvasBounds
import com.therealbluepandabear.pixapencil.algorithms.FloodFillAlgorithm
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.models.Coordinates

fun CanvasActivity.fillToolOnPixelTapped(coordinatesTapped: Coordinates) {
    if (canvasCommandsHelperInstance.coordinatesInCanvasBounds(coordinatesTapped, Tool.FillTool)) {
        val floodFillAlgorithmInstance = FloodFillAlgorithm(primaryAlgorithmInfoParameter)
        floodFillAlgorithmInstance.compute(Coordinates(coordinatesTapped.x, coordinatesTapped.y))
    }
}