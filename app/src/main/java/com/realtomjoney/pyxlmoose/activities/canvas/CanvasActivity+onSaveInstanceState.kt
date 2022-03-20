package com.realtomjoney.pyxlmoose.activities.canvas

import android.os.Bundle
import com.realtomjoney.pyxlmoose.converters.BitmapConverter

fun CanvasActivity.extendedOnSaveInstanceState(outState: Bundle) {
    outState.putInt(prevOrientationStr, resources.configuration.orientation)
    outState.putString(prevBitmapStrStr, BitmapConverter.convertBitmapToString(outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap))
}