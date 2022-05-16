package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.algorithms.AlgorithmInfoParameter
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance

fun CanvasActivity.initPrimaryAlgorithmInfoParameter() {
    primaryAlgorithmInfoParameter = AlgorithmInfoParameter.create(
        canvasCommandsHelperInstance,
        pixelGridViewInstance.pixelGridViewBitmap,
        viewModel.currentBitmapAction!!,
        getSelectedColor()
    )
}