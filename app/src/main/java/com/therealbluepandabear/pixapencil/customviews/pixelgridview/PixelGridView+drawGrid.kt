/*
 * PixaPencil
 * Copyright 2022  therealbluepandabear
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
            path1.lineTo(xm, measuredWidth.toFloat())
            path2.lineTo(measuredWidth.toFloat(), xm)
        } else {
            path1.lineTo(xm, measuredHeight.toFloat())
            path2.lineTo(measuredWidth.toFloat(), xm)
        }

        xm += scaleWidth
        path1.moveTo(xm, 0f)
        path2.moveTo(0f, xm)
    }

    canvas.drawPath(path1, gridPaint)
    canvas.drawPath(path2, gridPaint)
}