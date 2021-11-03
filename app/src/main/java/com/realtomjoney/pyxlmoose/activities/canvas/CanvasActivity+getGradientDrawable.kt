@file:Suppress("unused")

package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.Color
import android.graphics.drawable.GradientDrawable

fun CanvasActivity.extendedGetGradientDrawable(): GradientDrawable {
    val gd = GradientDrawable()
    gd.setColor(Color.RED)
    gd.cornerRadius = 10f
    gd.setStroke(2, Color.BLACK)
    return gd
}