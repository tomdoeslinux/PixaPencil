package com.therealbluepandabear.pixapencil.activities.canvas.ontapped

import android.graphics.Color
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.applyBitmapFilter
import com.therealbluepandabear.pixapencil.activities.canvas.getSelectedColor
import com.therealbluepandabear.pixapencil.activities.canvas.judgeUndoRedoStacks
import com.therealbluepandabear.pixapencil.utility.ColorFilterUtilities
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun CanvasActivity.extendedOnFilterTapped(filterType: String) {
    when (filterType) {
        StringConstants.Identifiers.ColorFilterIdentifier -> {
            canvasCommandsHelperInstance.applyBitmapFilter { ColorFilterUtilities.blendColor(it, getSelectedColor()) }
        }

        StringConstants.Identifiers.DarkenFilterIdentifier -> {
            canvasCommandsHelperInstance.applyBitmapFilter { ColorFilterUtilities.blendColor(it, Color.BLACK) }
        }

        StringConstants.Identifiers.LightenFilterIdentifier -> {
            canvasCommandsHelperInstance.applyBitmapFilter { ColorFilterUtilities.blendColor(it, Color.WHITE) }
        }

        StringConstants.Identifiers.InvertFilterIdentifier -> {
            canvasCommandsHelperInstance.applyBitmapFilter { ColorFilterUtilities.inverseRGB(it) }
        }

        StringConstants.Identifiers.GrayscaleFilterIdentifier -> {
            canvasCommandsHelperInstance.applyBitmapFilter { ColorFilterUtilities.grayScale(it) }
        }
    }

    judgeUndoRedoStacks()
}


