package com.therealbluepandabear.pixapencil.models

data class Coordinates(val x: Int, val y: Int) {
    fun convertToCoordinatesDouble(): CoordinatesDouble {
        return CoordinatesDouble(x.toDouble(), y.toDouble())
    }

    fun getHorizontallyReflectedCoordinates(bitmapHeight: Int): Coordinates {
        return Coordinates(x, (bitmapHeight - y) - 1)
    }

    fun getVerticallyReflectedCoordinates(bitmapWidth: Int): Coordinates {
        return Coordinates((bitmapWidth - x) - 1, y)
    }
}
