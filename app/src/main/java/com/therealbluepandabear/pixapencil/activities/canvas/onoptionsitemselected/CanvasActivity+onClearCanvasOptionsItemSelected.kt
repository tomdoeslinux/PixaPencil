package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.clearCanvas
import com.therealbluepandabear.pixapencil.activities.canvas.judgeUndoRedoStacks

fun CanvasActivity.onClearCanvasOptionsItemSelected() {
    val alertDialog = MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_App_MaterialAlertDialog)
        .setTitle(R.string.dialog_clear_canvas_title)
        .setMessage(R.string.dialog_clear_canvas_message)
        .setPositiveButton(R.string.generic_ok) { _, _ ->
            canvasCommandsHelperInstance.clearCanvas()
            judgeUndoRedoStacks()
        }
        .setNegativeButton(R.string.generic_cancel, null)

    alertDialog.show()
}