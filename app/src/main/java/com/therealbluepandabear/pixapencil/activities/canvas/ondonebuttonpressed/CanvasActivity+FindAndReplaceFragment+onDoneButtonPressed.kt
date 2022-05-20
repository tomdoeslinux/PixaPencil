package com.therealbluepandabear.pixapencil.activities.canvas.ondonebuttonpressed

import androidx.lifecycle.lifecycleScope
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.replacePixelsByColor
import com.therealbluepandabear.pixapencil.activities.canvas.judgeUndoRedoStacks
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun CanvasActivity.extendedOnDoneButtonPressed(colorToFind: Int?, colorToReplace: Int?) {
    supportFragmentManager.popBackStackImmediate()

    if (colorToFind != null && colorToReplace != null) {
        canvasCommandsHelperInstance.replacePixelsByColor(colorToFind, colorToReplace)

        lifecycleScope.launch {
            delay(50)
            judgeUndoRedoStacks()
        }
    }
}