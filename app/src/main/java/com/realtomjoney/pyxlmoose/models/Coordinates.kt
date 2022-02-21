package com.realtomjoney.pyxlmoose.models

data class Coordinates(val x: Int, val y: Int) {
    inner class CoordinatesDouble(val x: Double, val y: Double)

    fun convertToCoordinatesDouble(): CoordinatesDouble {
        return CoordinatesDouble(x.toDouble(), y.toDouble())
    }
}
