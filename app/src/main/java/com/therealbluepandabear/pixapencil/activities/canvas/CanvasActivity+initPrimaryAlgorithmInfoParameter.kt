package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.algorithms.AlgorithmInfoParameter
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance

fun initPrimaryAlgorithmInfoParameter() {
    primaryAlgorithmInfoParameter =  AlgorithmInfoParameter.create(
        pixelGridViewInstance,
        pixelGridViewInstance.pixelGridViewBitmap,
        pixelGridViewInstance.currentBitmapAction!!,
        getSelectedColor()
    )
}