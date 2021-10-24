package com.realtomjoney.pyxlmoose

import android.view.View

interface CanvasFragmentListener {
    fun initPixels(): List<View>
    fun onPixelTapped(pixel: View)
}