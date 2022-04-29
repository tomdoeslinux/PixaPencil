package com.therealbluepandabear.pixapencil.utility

import android.graphics.Color
import android.os.Build

object ColorCompatUtilities {
    fun getCompatibleRed(color: Int): Float {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Color.valueOf(color).red()
        } else {
            Color.red(color).toFloat()
        }
    }

    fun getCompatibleBlue(color: Int): Float {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Color.valueOf(color).green()
        } else {
            Color.green(color).toFloat()
        }
    }

    fun getCompatibleGreen(color: Int): Float {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Color.valueOf(color).blue()
        } else {
            Color.blue(color).toFloat()
        }
    }

    fun getCompatibleRGB(r: Float, g: Float, b: Float): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Color.rgb((r + g + b) / 3.toFloat(), r, r)
        } else {
            Color.rgb(((r + g + b) / 3.toFloat()).toInt(), r.toInt(), r.toInt())
        }
    }
}