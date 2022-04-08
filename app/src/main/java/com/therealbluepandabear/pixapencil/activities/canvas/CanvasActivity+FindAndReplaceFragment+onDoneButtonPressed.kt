package com.therealbluepandabear.pixapencil.activities.canvas

fun CanvasActivity.extendedOnDoneButtonPressed(colorToFind: Int?, colorToReplace: Int?) {
    if (colorToFind != null && colorToReplace != null) {
        outerCanvasInstance.canvasFragment.pixelGridViewInstance.replacePixelsByColor(colorToFind, colorToReplace)
    }

    navigateBack(findAndReplaceFragmentInstance)
}