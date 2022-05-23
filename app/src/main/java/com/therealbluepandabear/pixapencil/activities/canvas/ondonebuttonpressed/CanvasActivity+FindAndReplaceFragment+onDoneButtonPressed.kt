package com.therealbluepandabear.pixapencil.activities.canvas.ondonebuttonpressed

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.binding
import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.replacePixelsByColor
import com.therealbluepandabear.pixapencil.activities.canvas.judgeUndoRedoStacks

fun CanvasActivity.extendedOnDoneButtonPressed(colorToFind: Int?, colorToReplace: Int?) {
    supportFragmentManager.popBackStackImmediate()

    if (colorToFind != null && colorToReplace != null) {
        canvasCommandsHelperInstance.replacePixelsByColor(colorToFind, colorToReplace)

        binding.root.post {
            judgeUndoRedoStacks()
        }
    }
}