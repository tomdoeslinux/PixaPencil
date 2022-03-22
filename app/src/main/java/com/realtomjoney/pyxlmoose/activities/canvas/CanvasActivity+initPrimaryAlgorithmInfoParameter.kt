package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.algorithms.AlgorithmInfoParameter

fun initPrimaryAlgorithmInfoParameter() {
    primaryAlgorithmInfoParameter =  AlgorithmInfoParameter.pass(
        outerCanvasInstance.canvasFragment.myCanvasViewInstance,
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap,
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!,
        getSelectedColor()
    )
}