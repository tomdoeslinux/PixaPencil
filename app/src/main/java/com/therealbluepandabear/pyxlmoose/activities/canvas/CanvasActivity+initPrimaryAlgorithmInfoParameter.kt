package com.therealbluepandabear.pyxlmoose.activities.canvas

import com.therealbluepandabear.pyxlmoose.algorithms.AlgorithmInfoParameter

fun initPrimaryAlgorithmInfoParameter() {
    primaryAlgorithmInfoParameter =  AlgorithmInfoParameter.pass(
        outerCanvasInstance.canvasFragment.myCanvasViewInstance,
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap,
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!,
        getSelectedColor()
    )
}