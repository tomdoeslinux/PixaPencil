package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance

fun CanvasActivity.extendedOnDoneButtonPressed(colorToFind: Int?, colorToReplace: Int?) {
    if (colorToFind != null && colorToReplace != null) {
        pixelGridViewInstance.replacePixelsByColor(colorToFind, colorToReplace)
    }

    navigateBack(findAndReplaceFragmentInstance)
}