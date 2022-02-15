package com.realtomjoney.pyxlmoose.customviews.pixelgridview

import android.graphics.Bitmap
import android.graphics.Canvas
import com.realtomjoney.pyxlmoose.customviews.transparentbackgroundview.TransparentBackgroundView
import com.realtomjoney.pyxlmoose.fragments.outercanvas.binding

fun PixelGridView.extendedReplaceBitmap(newBitmap: Bitmap) {
    pixelGridViewBitmap = Bitmap.createBitmap(newBitmap.width, newBitmap.height, Bitmap.Config.ARGB_8888)
    pixelGridViewCanvas = Canvas(pixelGridViewBitmap)
    canvasSize = newBitmap.width
    pixelGridViewCanvas.drawBitmap(newBitmap, 0f, 0f, null)

    val transparent = TransparentBackgroundView(this.context, canvasSize)
    binding.defsq2.addView(transparent)

    invalidate()
}