package com.therealbluepandabear.pixapencil.activities.canvas.ondonebuttonpressed

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.replacePixelsByColor
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance

fun CanvasActivity.extendedOnDoneButtonPressed(colorToFind: Int?, colorToReplace: Int?) {
    supportFragmentManager.popBackStackImmediate()

    if (colorToFind != null && colorToReplace != null) {
        canvasCommandsHelperInstance.replacePixelsByColor(colorToFind, colorToReplace)
    }
}