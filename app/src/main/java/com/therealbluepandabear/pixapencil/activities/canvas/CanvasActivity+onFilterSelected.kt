package com.therealbluepandabear.pixapencil.activities.canvas

import android.graphics.Color
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.utility.ColorFilterUtilities
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun extendedOnFilterSelected(filterType: String) {
    when (filterType) {
        StringConstants.ColorFilterIdentifier -> {
            pixelGridViewInstance.applyBitmapFilter { ColorFilterUtilities.blendColor(it, getSelectedColor()) }
        }

        StringConstants.DarkenFilterIdentifier -> {
            pixelGridViewInstance.applyBitmapFilter { ColorFilterUtilities.blendColor(it, Color.BLACK) }
        }

        StringConstants.LightenFilterIdentifier -> {
            pixelGridViewInstance.applyBitmapFilter { ColorFilterUtilities.blendColor(it, Color.WHITE) }
        }

        StringConstants.InvertFilterIdentifier -> {
            pixelGridViewInstance.applyBitmapFilter { ColorFilterUtilities.inverseRGB(it) }
        }

        StringConstants.GrayscaleFilterIdentifier -> {
            pixelGridViewInstance.applyBitmapFilter { ColorFilterUtilities.grayScale(it) }
        }
    }
}


