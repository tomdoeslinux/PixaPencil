package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.Color
import com.realtomjoney.pyxlmoose.utility.ColorFilters
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun CanvasActivity.extendedOnFilterSelected(filterType: String) {
    canvasStates.add(canvasFragmentInstance.myCanvasViewInstance.saveData())

    when (filterType) {
        StringConstants.COLOR_FILTER_IDENTIFIER -> applyCanvasFilter { ColorFilters.blendColor(it, getSelectedColor()) }
        StringConstants.DARKEN_FILTER_IDENTIFIER -> applyCanvasFilter { ColorFilters.blendColor(it, Color.BLACK) }
        StringConstants.LIGHTEN_FILTER_IDENTIFIER -> applyCanvasFilter { ColorFilters.blendColor(it, Color.WHITE) }
        StringConstants.INVERT_FILTER_IDENTIFIER  -> applyCanvasFilter { ColorFilters.inverseRGB(it) }
        StringConstants.INVERT_RED_FILTER_IDENTIFIER ->  applyCanvasFilter { ColorFilters.inverseRed(it) }
        StringConstants.INVERT_GREEN_FILTER_IDENTIFIER ->  applyCanvasFilter { ColorFilters.inverseGreen(it) }
        StringConstants.INVERT_BLUE_FILTER_IDENTIFIER ->  applyCanvasFilter { ColorFilters.inverseBlue(it) }
        StringConstants.GRAYSCALE_FILTER_IDENTIFIER -> applyCanvasFilter { ColorFilters.grayScale(it) }
    }
}


