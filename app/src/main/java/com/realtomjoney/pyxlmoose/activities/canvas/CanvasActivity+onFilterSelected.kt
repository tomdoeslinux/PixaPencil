package com.realtomjoney.pyxlmoose.activities.canvas

fun CanvasActivity.extendedOnFilterSelected(filterType: String) {
    canvasStates.add(canvasFragmentInstance.myCanvasViewInstance.saveData())

    when (filterType) {
        "COLOR_FILTER" -> applyColorFilterToCanvas(getSelectedColor())
        "DARKEN_FILTER" -> darkenCanvas()
        "LIGHTEN_FILTER" -> lightenCanvas()
    }
}