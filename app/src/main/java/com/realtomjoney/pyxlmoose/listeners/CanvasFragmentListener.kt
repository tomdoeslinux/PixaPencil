package com.realtomjoney.pyxlmoose.listeners

import android.view.View

interface CanvasFragmentListener {
    fun initPixels(): List<View>
    fun onPixelTapped(pixel: View)
}