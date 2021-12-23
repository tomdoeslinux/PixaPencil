package com.realtomjoney.pyxlmoose.utility

import android.graphics.Color
import androidx.core.graphics.ColorUtils

object ColorFilters {
    fun inverseRGB(color: Int) = color xor 0x00ffffff

    fun inverseRed(color: Int) = color xor 0x00ff0000
    fun inverseGreen(color: Int) = color xor 0x0000ff00
    fun inverseBlue(color: Int) = color xor 0x0000000ff

    fun grayScale(_color: Int, strength: Int = 10): Int {
        var color = _color

        for (i in 0..strength) {
            val r = Color.valueOf(color).red()
            val g = Color.valueOf(color).green()
            val b = Color.valueOf(color).blue()

            color = (Color.rgb((r + g + b) / 3.toFloat(), r, r))
        }

        return color
    }

    fun blendColor(color: Int, blend: Int, ratio: Float = 0.2f): Int {
        return ColorUtils.blendARGB(color, blend, ratio)
    }
}