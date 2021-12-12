package com.realtomjoney.pyxlmoose.customviews.mycanvasview

import com.realtomjoney.pyxlmoose.models.Pixel

fun MyCanvasView.extendedSaveData(): List<Pixel> {
    val data = mutableListOf<Pixel>()

    for (pair in rectangles) {
        data.add(Pixel(pair.value?.color))
    }

    return data
}
