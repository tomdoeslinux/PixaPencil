package com.therealbluepandabear.pyxlmoose.listeners

import com.therealbluepandabear.pyxlmoose.models.BitmapAction
import com.therealbluepandabear.pyxlmoose.models.Coordinates

interface CanvasFragmentListener {
    fun onPixelTapped(coordinatesTapped: Coordinates)
    fun onActionUp()
    fun onRedoActionCompleted(undoStack: List<BitmapAction>)
    fun onUndoActionCompleted(undoStack: List<BitmapAction>, bitmapActionData: List<BitmapAction>)
}