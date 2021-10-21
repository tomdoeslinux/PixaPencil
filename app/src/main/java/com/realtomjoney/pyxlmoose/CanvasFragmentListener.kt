package com.realtomjoney.pyxlmoose

interface CanvasFragmentListener {
    fun initPixels(): List<Pixel>
    fun onPixelTapped(pixel: Pixel)
}