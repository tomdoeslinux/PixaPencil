package com.therealbluepandabear.pixapencil.customviews.pixelgridview

import android.graphics.Canvas

fun PixelGridView.extendedDrawGrid(canvas: Canvas) {
    gridPaint.isAntiAlias = outerCanvasInstance.cardViewParent.scaleX <= 3
    gridPaint.alpha = outerCanvasInstance.cardViewParent.scaleX.toInt() * 100

    xm = 0f
    path1.reset()
    path2.reset()

    val dvr = if (canvasWidth >= canvasHeight) {
        canvasWidth
    } else {
        canvasHeight
    }

    for (i in 0 until dvr) {
        if (canvasWidth >= canvasHeight) {
            path1.lineTo(xm, width.toFloat())
            path2.lineTo(width.toFloat(), xm)
        } else {
            path1.lineTo(xm, height.toFloat())
            path2.lineTo(width.toFloat(), xm)
        }

        xm += scaleWidth
        path1.moveTo(xm, 0f)
        path2.moveTo(0f, xm)
    }

    canvas.drawPath(path1, gridPaint)
    canvas.drawPath(path2, gridPaint)
}