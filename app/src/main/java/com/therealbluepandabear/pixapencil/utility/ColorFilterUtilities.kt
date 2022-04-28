package com.therealbluepandabear.pixapencil.utility

import android.graphics.Color
import android.os.Build
import androidx.core.graphics.ColorUtils

object ColorFilterUtilities {
    fun inverseRGB(color: Int): Int {
        return color xor 0x00ffffff
    }

    fun grayScale(_color: Int, strength: Int = 10): Int {
        var color = _color

        for (i in 0..strength) {
            val r: Float = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Color.valueOf(color).red()
            } else {
                Color.red(color).toFloat()
            }

            val g: Float = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Color.valueOf(color).green()
            } else {
                Color.green(color).toFloat()
            }

            val b: Float = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Color.valueOf(color).blue()
            } else {
                Color.blue(color).toFloat()
            }

            color = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                (Color.rgb((r + g + b) / 3.toFloat(), r, r))
            } else {
                (Color.rgb(((r + g + b) / 3.toFloat()).toInt(), r.toInt(), r.toInt()))
            }
        }

        return color
    }

    fun blendColor(color: Int, blend: Int, ratio: Float = 0.2f): Int {
        return ColorUtils.blendARGB(color, blend, ratio)
    }
}