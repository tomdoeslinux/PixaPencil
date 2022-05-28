package com.therealbluepandabear.pixapencil.enums

enum class RotationValue(val degrees: Int, val clockwise: Boolean = false) {
    NinetyAntiClockwise(90, false),
    NinetyClockwise(90, true),
    OneEighty(180),
}