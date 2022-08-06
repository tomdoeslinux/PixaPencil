package com.therealbluepandabear.pixapencil.activities.canvas.onactionup

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity

fun CanvasActivity.lineToolOnActionUp() {
    shapeOrigin = null
    firstShapeDrawn = false
    coordinates = null
    viewModel.undoStack.add(viewModel.currentBitmapAction!!)
}