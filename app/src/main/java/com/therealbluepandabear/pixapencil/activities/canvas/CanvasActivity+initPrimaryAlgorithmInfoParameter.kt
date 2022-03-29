package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.algorithms.AlgorithmInfoParameter

fun initPrimaryAlgorithmInfoParameter() {
    primaryAlgorithmInfoParameter =  AlgorithmInfoParameter.pass(
        outerCanvasInstance.canvasFragment.myCanvasViewInstance,
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap,
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!,
        getSelectedColor()
    )
}