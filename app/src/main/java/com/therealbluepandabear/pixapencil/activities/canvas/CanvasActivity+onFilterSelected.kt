package com.therealbluepandabear.pixapencil.activities.canvas

import android.graphics.Color
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.utility.ColorFilterUtilities
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun extendedOnFilterTapped(filterType: String) {
    when (filterType) {
        StringConstants.Identifiers.ColorFilterIdentifier -> {
            pixelGridViewInstance.applyBitmapFilter { ColorFilterUtilities.blendColor(it, getSelectedColor()) }
        }

        StringConstants.Identifiers.DarkenFilterIdentifier -> {
            pixelGridViewInstance.applyBitmapFilter { ColorFilterUtilities.blendColor(it, Color.BLACK) }
        }

        StringConstants.Identifiers.LightenFilterIdentifier -> {
            pixelGridViewInstance.applyBitmapFilter { ColorFilterUtilities.blendColor(it, Color.WHITE) }
        }

        StringConstants.Identifiers.InvertFilterIdentifier -> {
            pixelGridViewInstance.applyBitmapFilter { ColorFilterUtilities.inverseRGB(it) }
        }

        StringConstants.Identifiers.GrayscaleFilterIdentifier -> {
            pixelGridViewInstance.applyBitmapFilter { ColorFilterUtilities.grayScale(it) }
        }
    }
}


