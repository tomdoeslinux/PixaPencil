package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.Color
import android.graphics.drawable.GradientDrawable

fun extendedGetGradientDrawable(): GradientDrawable {
    return GradientDrawable().apply {
        setColor(Color.RED)
        cornerRadius = 10f
        setStroke(2, Color.BLACK)
    }
}