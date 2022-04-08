package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.algorithms.AlgorithmInfoParameter

fun initPrimaryAlgorithmInfoParameter() {
    primaryAlgorithmInfoParameter =  AlgorithmInfoParameter.create(
        outerCanvasInstance.canvasFragment.pixelGridViewInstance,
        outerCanvasInstance.canvasFragment.pixelGridViewInstance.pixelGridViewBitmap,
        outerCanvasInstance.canvasFragment.pixelGridViewInstance.currentBitmapAction!!,
        getSelectedColor()
    )
}