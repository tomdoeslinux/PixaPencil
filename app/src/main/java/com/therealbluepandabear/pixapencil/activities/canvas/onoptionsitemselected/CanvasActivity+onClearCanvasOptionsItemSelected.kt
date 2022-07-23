package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.clearCanvas
import com.therealbluepandabear.pixapencil.activities.canvas.judgeUndoRedoStacks
import com.therealbluepandabear.pixapencil.extensions.showDialog

fun CanvasActivity.onClearCanvasOptionsItemSelected() {
    showDialog(
        getString(R.string.dialog_clear_canvas_title),
        getString(R.string.dialog_clear_canvas_message),
        getString(R.string.generic_ok),
        { _, _ ->
            canvasCommandsHelperInstance.clearCanvas()
            judgeUndoRedoStacks()
        }, getString(R.string.generic_cancel), { _, _ -> })
}