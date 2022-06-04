package com.therealbluepandabear.pixapencil.models

data class DitherBrush(val algorithm: (Coordinates) -> Boolean) {
    var id: Int = 0
}