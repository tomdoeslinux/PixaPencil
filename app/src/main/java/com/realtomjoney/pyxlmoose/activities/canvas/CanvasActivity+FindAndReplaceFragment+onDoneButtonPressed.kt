package com.realtomjoney.pyxlmoose.activities.canvas

fun CanvasActivity.extendedOnDoneButtonPressed(colorToFind: Int?, colorToReplace: Int?) {
    if (colorToFind != null && colorToReplace != null) {
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.replacePixelsByColor(colorToFind, colorToReplace)
    }

    navigateBack(findAndReplaceFragmentInstance)
}