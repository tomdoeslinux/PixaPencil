package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.algorithms.SprayAlgorithm
import com.realtomjoney.pyxlmoose.models.Coordinates

fun sprayToolOnPixelTapped(coordinatesTapped: Coordinates) {
    if (!sprayAlgorithmInstanceInitialized) {
        val s1 = SprayAlgorithm(primaryAlgorithmInfoParameter)
        sprayAlgorithmInstance = s1
    }

    sprayAlgorithmInstance.compute(coordinatesTapped)
}