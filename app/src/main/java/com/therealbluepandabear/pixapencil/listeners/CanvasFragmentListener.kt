package com.therealbluepandabear.pixapencil.listeners

import com.therealbluepandabear.pixapencil.models.Coordinates

interface CanvasFragmentListener {
    fun onViewLoaded()
    fun onPixelTapped(coordinatesTapped: Coordinates)
    fun onActionUp()
    fun dispatchTouchEvent()
}