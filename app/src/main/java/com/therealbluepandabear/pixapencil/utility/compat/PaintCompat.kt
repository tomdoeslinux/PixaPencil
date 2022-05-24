package com.therealbluepandabear.pixapencil.utility.compat

import android.graphics.Paint
import android.os.Build

object PaintCompat {
    fun getSDK28PaintOrNull(): Paint? {
        val preSDK28Paint = Paint()
        preSDK28Paint.isFilterBitmap = false

        return if (Build.VERSION.SDK_INT <= 28) {
            preSDK28Paint
        } else {
            null
        }
    }
}