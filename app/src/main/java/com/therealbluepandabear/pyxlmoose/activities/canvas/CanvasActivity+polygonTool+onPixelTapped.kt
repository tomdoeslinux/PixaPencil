package com.therealbluepandabear.pyxlmoose.activities.canvas

import com.therealbluepandabear.pyxlmoose.algorithms.LineAlgorithm
import com.therealbluepandabear.pyxlmoose.models.Coordinates
import com.therealbluepandabear.pyxlmoose.utility.Flags

fun polygonToolOnPixelTapped(coordinatesTapped: Coordinates) {
    Flags.DisableActionMove = true

    val lineAlgorithmInstance = LineAlgorithm(primaryAlgorithmInfoParameter)

    polygonCoordinates.add(coordinatesTapped)

    if (polygonCoordinates.size > 1) {
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.undo()

        for (i in 0 until polygonCoordinates.size - 2) {
            lineAlgorithmInstance.compute(
                polygonCoordinates[cindx - (i + 1)],
                polygonCoordinates[cindx - i]
            )
        }

        lineAlgorithmInstance.compute(polygonCoordinates[cindx], polygonCoordinates[cindx + 1])
        lineAlgorithmInstance.compute(polygonCoordinates[0], polygonCoordinates[cindx + 1])

        cindx += 1
    }
}