package com.realtomjoney.pyxlmoose.activities.canvas

fun CanvasActivity.extendedOnFilterSelected(filterType: String) {
    canvasStates.add(canvasFragmentInstance.myCanvasViewInstance.saveData())

    when (filterType) {
        "COLOR_FILTER" -> applyColorFilterToCanvas(getSelectedColor())
        "DARKEN_FILTER" -> darkenCanvas()
        "LIGHTEN_FILTER" -> lightenCanvas()
        "INVERT_FILTER" -> applyCanvasFilter { flipBits(it) }
        "INVERT_RED_FILTER" ->  applyCanvasFilter { flipRed(it) }
        "INVERT_GREEN_FILTER" -> applyCanvasFilter { flipGreen(it) }
        "INVERT_BLUE_FILTER" -> applyCanvasFilter { flipBlue(it) }
        "GRAYSCALE_FILTER" -> applyCanvasFilter { performCustomGrayscaleAlgorithm(it) }
        "GRAYSCALE_FILTER_TWO" -> applyCanvasFilter { performCustomGrayscaleAlgorithm(it, 0) }
        "GRAYSCALE_FILTER_THREE" -> applyCanvasFilter { performCustomGrayscaleAlgorithm(it, 1) }
    }
}


