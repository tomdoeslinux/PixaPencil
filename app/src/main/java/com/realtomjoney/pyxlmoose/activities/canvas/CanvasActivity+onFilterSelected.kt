package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.Color
import com.realtomjoney.pyxlmoose.utility.ColorFilterUtilities
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun CanvasActivity.extendedOnFilterSelected(filterType: String) {
    when (filterType) {
        StringConstants.COLOR_FILTER_IDENTIFIER ->  outerCanvasInstance.canvasFragment.myCanvasViewInstance.applyBitmapFilter { ColorFilterUtilities.blendColor(it, getSelectedColor()) }
        StringConstants.DARKEN_FILTER_IDENTIFIER ->  outerCanvasInstance.canvasFragment.myCanvasViewInstance.applyBitmapFilter { ColorFilterUtilities.blendColor(it, Color.BLACK) }
        StringConstants.LIGHTEN_FILTER_IDENTIFIER ->  outerCanvasInstance.canvasFragment.myCanvasViewInstance.applyBitmapFilter { ColorFilterUtilities.blendColor(it, Color.WHITE) }
        StringConstants.INVERT_FILTER_IDENTIFIER  ->  outerCanvasInstance.canvasFragment.myCanvasViewInstance.applyBitmapFilter { ColorFilterUtilities.inverseRGB(it) }
        StringConstants.INVERT_RED_FILTER_IDENTIFIER ->   outerCanvasInstance.canvasFragment.myCanvasViewInstance.applyBitmapFilter { ColorFilterUtilities.inverseRed(it) }
        StringConstants.INVERT_GREEN_FILTER_IDENTIFIER ->  outerCanvasInstance.canvasFragment.myCanvasViewInstance.applyBitmapFilter { ColorFilterUtilities.inverseGreen(it) }
        StringConstants.INVERT_BLUE_FILTER_IDENTIFIER ->  outerCanvasInstance.canvasFragment.myCanvasViewInstance.applyBitmapFilter { ColorFilterUtilities.inverseBlue(it) }
        StringConstants.GRAYSCALE_FILTER_IDENTIFIER -> outerCanvasInstance.canvasFragment.myCanvasViewInstance.applyBitmapFilter { ColorFilterUtilities.grayScale(it) }
    }
}


