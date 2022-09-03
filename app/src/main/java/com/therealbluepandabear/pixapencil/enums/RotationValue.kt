package com.therealbluepandabear.pixapencil.enums

/**
 * There is a bug in which when you rotate a view 180 degrees, its drop shadow disappears.
 * This has been reported to Google but it hasn't been fixed in some compat libraries.
 * Hopefully it gets fixed in other libraries soon, but for now we can simply rotate it by 179.9 degrees and it seems to fix it.
 * The difference isn't noticeable.
 */

enum class RotationValue(val degrees: Float, val clockwise: Boolean = false) {
    NinetyAntiClockwise(90f),
    NinetyClockwise(90f, true),
    OneEighty(180f),
}