package com.realtomjoney.pyxlmoose.listeners

import android.graphics.RectF
import com.realtomjoney.pyxlmoose.customviews.mycanvasview.MyCanvasView

// ✔ CLEAN

interface CanvasFragmentListener {
    fun onPixelTapped(canvasInstance: MyCanvasView, rectTapped: RectF)
    fun onActionUp()
}