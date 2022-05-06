package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped.*
import com.therealbluepandabear.pixapencil.algorithms.RectangleAlgorithm
import com.therealbluepandabear.pixapencil.enums.ToolFamily
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance

fun rectangleToolOnActionUp() {
    if (coordinates != null && rectangleOrigin != null) {
        val rectAlg: RectangleAlgorithm? =
            if (currentTool.toolFamily == ToolFamily.Rectangle && currentTool.outlined == false) {
                RectangleAlgorithm(primaryAlgorithmInfoParameter)
            } else {
                null
            }

        rectAlg?.compute(rectangleOrigin!!, coordinates!!)
    }

    pixelGridViewInstance.bitmapActionData.add(
        pixelGridViewInstance.currentBitmapAction!!
    )

    coordinates = null
    rectangleOrigin = null
    rectangleMode_hasLetGo = false
    rectangleAlgorithmInstance = null
    squareAlgorithmInstance = null
    first = true
}