package com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped

import com.therealbluepandabear.pixapencil.activities.canvas.primaryAlgorithmInfoParameter
import com.therealbluepandabear.pixapencil.algorithms.FloodFillAlgorithm
import com.therealbluepandabear.pixapencil.models.Coordinates

fun fillToolOnPixelTapped(coordinatesTapped: Coordinates) {
    val floodFillAlgorithmInstance = FloodFillAlgorithm(primaryAlgorithmInfoParameter)

    floodFillAlgorithmInstance.compute(Coordinates(coordinatesTapped.x, coordinatesTapped.y))
}