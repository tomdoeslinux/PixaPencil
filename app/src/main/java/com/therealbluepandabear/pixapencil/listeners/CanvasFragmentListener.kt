package com.therealbluepandabear.pixapencil.listeners

import com.therealbluepandabear.pixapencil.models.BitmapAction
import com.therealbluepandabear.pixapencil.models.Coordinates

interface CanvasFragmentListener {
    fun onViewLoaded()
    fun onPixelTapped(coordinatesTapped: Coordinates)
    fun onActionUp()
    fun onRedoActionCompleted(undoStack: List<BitmapAction>)
    fun onUndoActionCompleted(undoStack: List<BitmapAction>, bitmapActionData: List<BitmapAction>)
}