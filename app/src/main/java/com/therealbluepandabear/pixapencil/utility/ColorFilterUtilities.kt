package com.therealbluepandabear.pixapencil.utility

import androidx.core.graphics.ColorUtils

object ColorFilterUtilities {
    fun inverseRGB(color: Int): Int {
        return color xor 0x00ffffff
    }

    fun grayScale(_color: Int, strength: Int = 10): Int {
        var color = _color

        for (i in 0..strength) {
            val r: Float = ColorCompat.getCompatibleRed(color)
            val g: Float = ColorCompat.getCompatibleGreen(color)
            val b: Float = ColorCompat.getCompatibleBlue(color)

            color = ColorCompat.getCompatibleRGB((r + g + b) / 3.toFloat(), r, r)
        }

        return color
    }

    fun blendColor(color: Int, blend: Int, ratio: Float = 0.2f): Int {
        return ColorUtils.blendARGB(color, blend, ratio)
    }
}