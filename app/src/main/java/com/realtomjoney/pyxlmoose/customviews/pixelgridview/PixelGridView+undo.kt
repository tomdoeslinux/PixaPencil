package com.realtomjoney.pyxlmoose.customviews.pixelgridview

fun PixelGridView.extendedUndo() {
    if (bitmapActionData.size > 0) {
        for ((key, value) in bitmapActionData.last().actionData.distinctBy { it.xyPosition }) {
            pixelGridViewBitmap.setPixel(key.x, key.y, value)
        }

        invalidate()
        bitmapActionData.removeLast()
    }
}