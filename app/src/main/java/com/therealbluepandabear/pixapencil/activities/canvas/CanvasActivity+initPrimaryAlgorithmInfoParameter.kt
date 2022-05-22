package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.algorithms.AlgorithmInfoParameter
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.utility.ObjectConstants

fun CanvasActivity.initPrimaryAlgorithmInfoParameter() {
    primaryAlgorithmInfoParameter = AlgorithmInfoParameter.create(
        canvasCommandsHelperInstance,
        pixelGridViewInstance.pixelGridViewBitmap,
        viewModel.currentBitmapAction!!,
        getSelectedColor()
    )
    ObjectConstants.PrimaryAlgorithmInfoParameter = primaryAlgorithmInfoParameter
}