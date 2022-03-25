package com.therealbluepandabear.pyxlmoose.activities.canvas

import com.therealbluepandabear.pyxlmoose.algorithms.FloodFillAlgorithm
import com.therealbluepandabear.pyxlmoose.models.Coordinates

fun fillToolOnPixelTapped(coordinatesTapped: Coordinates) {
    val floodFillAlgorithmInstance = FloodFillAlgorithm(primaryAlgorithmInfoParameter)

    floodFillAlgorithmInstance.compute(Coordinates(coordinatesTapped.x, coordinatesTapped.y))
}