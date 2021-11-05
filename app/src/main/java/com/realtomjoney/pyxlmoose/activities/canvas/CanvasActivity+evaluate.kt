package com.realtomjoney.pyxlmoose.activities.canvas

import android.view.MotionEvent
import kotlin.math.pow

fun CanvasActivity.extendedEvaluate(event: MotionEvent) {
    var count = 0
    for (px in data) {
        val originalPos = IntArray(2)
        px.getLocationInWindow(originalPos)

        val sensitivity = ((400) * (0.9.pow(spanCount)) + 8.9)

        if (originalPos[0] - event.x.toDouble() in -sensitivity..sensitivity && originalPos[1] - event.y.toDouble() in -sensitivity..sensitivity) {
            onPixelTapped(px)
            count++

            if (count == 1) return
        }
    }
}