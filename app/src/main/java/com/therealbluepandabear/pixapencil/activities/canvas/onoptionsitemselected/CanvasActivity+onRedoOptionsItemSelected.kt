package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.redo

fun CanvasActivity.onRedoOptionsItemSelected() {
   canvasCommandsHelperInstance.redo()
}