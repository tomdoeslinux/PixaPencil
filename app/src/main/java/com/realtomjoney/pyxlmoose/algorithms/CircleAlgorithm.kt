package com.realtomjoney.pyxlmoose.algorithms

import com.realtomjoney.pyxlmoose.models.Coordinates
import com.realtomjoney.pyxlmoose.models.CoordinatesDouble

class CircleAlgorithm(private val algorithmInfo: AlgorithmInfoParameter, private val filledMode: Boolean = false) {
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

            val mp = MidpointCircleAlgorithm(algorithmInfo, filledMode = filledMode)
            mp.compute(Coordinates(mx.toInt(), my.toInt()), radius)
        } else {
            x2d--
            y2d--

            midpoint = CoordinatesDouble(((x1d + x2d) / 2), (y1d + y2d) / 2)

            val mxN = midpoint.x

            val radius = (p2.x - mxN.toInt()) - 1

            val mp = MidpointCircleAlgorithm(algorithmInfo, true, filledMode = filledMode)
            mp.compute(Coordinates(mx.toInt(), my.toInt()), radius)
        }
    }
}