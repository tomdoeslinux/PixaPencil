package com.therealbluepandabear.pixapencil.activities.canvas

import android.graphics.Color
import com.therealbluepandabear.pixapencil.utility.ColorFilterUtilities
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun extendedOnFilterSelected(filterType: String) {
    when (filterType) {
        StringConstants.ColorFilterIdentifier -> {
            outerCanvasInstance.canvasFragment.pixelGridViewInstance.applyBitmapFilter { ColorFilterUtilities.blendColor(it, getSelectedColor()) }
        }

        StringConstants.DarkenFilterIdentifier -> {
            outerCanvasInstance.canvasFragment.pixelGridViewInstance.applyBitmapFilter { ColorFilterUtilities.blendColor(it, Color.BLACK) }
        }

        StringConstants.LightenFilterIdentifier -> {
            outerCanvasInstance.canvasFragment.pixelGridViewInstance.applyBitmapFilter { ColorFilterUtilities.blendColor(it, Color.WHITE) }
        }

        StringConstants.InvertFilterIdentifier -> {
            outerCanvasInstance.canvasFragment.pixelGridViewInstance.applyBitmapFilter { ColorFilterUtilities.inverseRGB(it) }
        }

        StringConstants.GrayscaleFilterIdentifier -> {
            outerCanvasInstance.canvasFragment.pixelGridViewInstance.applyBitmapFilter { ColorFilterUtilities.grayScale(it) }
        }
    }
}


