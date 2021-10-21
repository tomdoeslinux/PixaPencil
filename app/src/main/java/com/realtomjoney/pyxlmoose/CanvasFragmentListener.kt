package com.realtomjoney.pyxlmoose

interface CanvasFragmentListener {
    fun initPixels(): ArrayList<Pixel>
    fun onPixelTapped(pixel: Pixel)
}