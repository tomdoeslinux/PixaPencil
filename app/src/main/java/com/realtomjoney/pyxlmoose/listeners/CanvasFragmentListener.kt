package com.realtomjoney.pyxlmoose.listeners

import com.realtomjoney.pyxlmoose.models.XYPosition

interface CanvasFragmentListener {
    fun onPixelTapped(coordinatesTapped: XYPosition)
    fun onActionUp()
}