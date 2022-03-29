package com.therealbluepandabear.pixapencil.models

data class Coordinates(val x: Int, val y: Int) {
    fun convertToCoordinatesDouble(): CoordinatesDouble {
        return CoordinatesDouble(x.toDouble(), y.toDouble())
    }
}
