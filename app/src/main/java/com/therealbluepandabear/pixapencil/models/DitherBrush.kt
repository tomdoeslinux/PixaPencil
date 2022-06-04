package com.therealbluepandabear.pixapencil.models

data class DitherBrush(val ditherBrushId: Int, val algorithm: (Coordinates) -> Boolean)