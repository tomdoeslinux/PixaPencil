package com.realtomjoney.pyxlmoose.activities.canvas

fun extendedGetCanvasColors(): List<Int> {
    val canvasColors = mutableListOf<Int>()

    for (it in canvasFragmentInstance.myCanvasViewInstance.saveData()) {
        if (it.pixelColor != null) {
            if (!canvasColors.contains((it.pixelColor!!))) {
                canvasColors.add(it.pixelColor!!)
            }
        }
    }

    return canvasColors
}