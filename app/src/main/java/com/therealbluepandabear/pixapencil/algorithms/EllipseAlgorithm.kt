package com.therealbluepandabear.pixapencil.algorithms

import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.models.CoordinatesDouble
import kotlin.math.abs

class EllipseAlgorithm(val algorithmInfo: AlgorithmInfoParameter, private val filledMode: Boolean = false) {
    fun compute(p1: Coordinates, p2: Coordinates) {
        val p1Double = p1.convertToCoordinatesDouble()
        val p2Double = p2.convertToCoordinatesDouble()

        val x1d = p1Double.x
        val y1d = p1Double.y

        val x2d = p2Double.x
        val y2d = p2Double.y

        val midpoint = CoordinatesDouble(((x1d + x2d) / 2), (y1d + y2d) / 2)

        val mx = midpoint.x
        val my = midpoint.y

        val rY = abs((p2.y - p1.y) / 2)
        val rX = abs((p2.x - p1.x) / 2)

        if (mx % 1.0 == 0.0 && my % 1.0 == 0.0) {
            val mp = MidpointEllipseAlgorithm(algorithmInfo, filledMode = filledMode)
            mp.compute(Coordinates(mx.toInt(), my.toInt()), rX, rY)
        } else if (mx % 1.0 != 0.0 && my % 1.0 == 0.0) {
            val mp = MidpointEllipseAlgorithm(algorithmInfo, xDEC = true, yDEC = false, filledMode = filledMode)
            mp.compute(Coordinates(mx.toInt(), my.toInt()), rX, rY)
        } else if (mx % 1.0 == 0.0 && my % 1.0 != 0.0) {
            val mp = MidpointEllipseAlgorithm(algorithmInfo, xDEC = false, yDEC = true, filledMode = filledMode)
            mp.compute(Coordinates(mx.toInt(), my.toInt()), rX, rY)
        } else {
            val mp = MidpointEllipseAlgorithm(algorithmInfo, xDEC = true, yDEC = true, filledMode = filledMode)
            mp.compute(Coordinates(mx.toInt(), my.toInt()), rX, rY)
        }
    }
}