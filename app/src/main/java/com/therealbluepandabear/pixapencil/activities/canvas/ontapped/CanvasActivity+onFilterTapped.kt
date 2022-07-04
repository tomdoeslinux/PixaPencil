package com.therealbluepandabear.pixapencil.activities.canvas.ontapped

import android.graphics.Color
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.applyBitmapFilter
import com.therealbluepandabear.pixapencil.activities.canvas.getSelectedColor
import com.therealbluepandabear.pixapencil.activities.canvas.judgeUndoRedoStacks
import com.therealbluepandabear.pixapencil.utility.general.ColorFilterUtilities
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun CanvasActivity.extendedOnFilterTapped(filterType: String) {
    when (filterType) {
        StringConstants.Identifiers.COLOR_FILTER_IDENTIFIER -> {
            canvasCommandsHelperInstance.applyBitmapFilter { ColorFilterUtilities.blendColor(it, getSelectedColor()) }
        }

        StringConstants.Identifiers.DARKEN_FILTER_IDENTIFIER -> {
            canvasCommandsHelperInstance.applyBitmapFilter { ColorFilterUtilities.blendColor(it, Color.BLACK) }
        }

        StringConstants.Identifiers.LIGHTEN_FILTER_IDENTIFIER -> {
            canvasCommandsHelperInstance.applyBitmapFilter { ColorFilterUtilities.blendColor(it, Color.WHITE) }
        }

        StringConstants.Identifiers.INVERT_FILTER_IDENTIFIER -> {
            canvasCommandsHelperInstance.applyBitmapFilter { ColorFilterUtilities.inverseRGB(it) }
        }

        StringConstants.Identifiers.GRAYSCALE_FILTER_IDENTIFIER -> {
            canvasCommandsHelperInstance.applyBitmapFilter { ColorFilterUtilities.grayScale(it) }
        }
    }

    judgeUndoRedoStacks()
}


