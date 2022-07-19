package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.algorithms.AlgorithmInfoParameter
import com.therealbluepandabear.pixapencil.utility.constants.ObjectConstants

fun CanvasActivity.initPrimaryAlgorithmInfoParameter() {
    primaryAlgorithmInfoParameter = AlgorithmInfoParameter.create(
        canvasCommandsHelperInstance,
        binding.activityCanvasPixelGridView.pixelGridViewBitmap,
        viewModel.currentBitmapAction!!,
        getSelectedColor()
    )
    ObjectConstants.PrimaryAlgorithmInfoParameter = primaryAlgorithmInfoParameter
}