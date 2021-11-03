@file:Suppress("unused")

package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.drawable.ColorDrawable

fun CanvasActivity.extendedGetCanvasColors(): List<Int> {
    val canvasColors = mutableListOf<Int>()

    data.forEach { if (it.background != null) { if (!canvasColors.contains((it.background as ColorDrawable).color)) canvasColors.add((it.background as ColorDrawable).color) } }

    return canvasColors
}