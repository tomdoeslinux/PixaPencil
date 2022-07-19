package com.therealbluepandabear.pixapencil.customviews.pixelgridview

import android.graphics.Canvas
import android.view.View

fun PixelGridView.extendedDrawGrid(canvas: Canvas) {
    if (parent is View && parent != null) {
        gridPaint.isAntiAlias = (parent as View).scaleX <= 3
        gridPaint.alpha = (parent as View).scaleX.toInt() * 100
    }

    xm = 0f
    path1.reset()
    path2.reset()

    val dvr = if (pixelGridViewBitmap.width >= pixelGridViewBitmap.height) {
        pixelGridViewBitmap.width
    } else {
        pixelGridViewBitmap.height
    }

    for (i in 0 until dvr) {
        if (pixelGridViewBitmap.width >= pixelGridViewBitmap.height) {
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