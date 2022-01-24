package com.realtomjoney.pyxlmoose.customviews.pixelgridview

fun PixelGridView.extendedUndo() {
    if (bitmapActionData.size > 0) {

        if (!bitmapActionData.last().isFilterBased) {
            for ((key, value) in bitmapActionData.last().actionData.distinctBy { it.xyPosition }) {
                pixelGridViewBitmap.setPixel(key.x, key.y, value)
            }
        } else {
            for ((key, value) in bitmapActionData.last().actionData) {
                pixelGridViewBitmap.setPixel(key.x, key.y, value)
            }
        }

        invalidate()
        bitmapActionData.removeLast()
    }
}