package com.therealbluepandabear.pixapencil.activities.canvas.onactionup

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.extensions.doAddLast

fun CanvasActivity.lineToolOnActionUp() {
    shapeOrigin = null
    firstShapeDrawn = false
    coordinates = null
    viewModel.undoStack.doAddLast(viewModel.currentBitmapAction!!)
}