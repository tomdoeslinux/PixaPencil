package com.therealbluepandabear.pixapencil.algorithms

import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.models.CoordinatesDouble

class CircleAlgorithm(algorithmInfo: AlgorithmInfoParameter, filledMode: Boolean = false) {
    private val filledMp = MidpointCircleAlgorithm(algorithmInfo, filledMode = filledMode)
    private val filledMpEvenDiameterMode = MidpointCircleAlgorithm(algorithmInfo, true, filledMode = filledMode)

    fun compute(p1: Coordinates, p2: Coordinates) {
        val p1Double = p1.convertToCoordinatesDouble()
        val p2Double = p2.convertToCoordinatesDouble()

        val x1d = p1Double.x
        val y1d = p1Double.y

        var x2d = p2Double.x
        var y2d = p2Double.y

        var midpoint = CoordinatesDouble(((x1d + x2d) / 2), (y1d + y2d) / 2)

        val mx = midpoint.x
        val my = midpoint.y

        if (mx % 1.0 == 0.0 || my % 1.0 == 0.0) {
            val radius = (p2.x - mx.toInt())
            filledMp.compute(Coordinates(mx.toInt(), my.toInt()), radius)
        } else {
            x2d--
            y2d--

            midpoint = CoordinatesDouble(((x1d + x2d) / 2), (y1d + y2d) / 2)

            val mxN = midpoint.x
            val radius = (p2.x - mxN.toInt()) - 1

            filledMpEvenDiameterMode.compute(Coordinates(mx.toInt(), my.toInt()), radius)
        }
    }
}