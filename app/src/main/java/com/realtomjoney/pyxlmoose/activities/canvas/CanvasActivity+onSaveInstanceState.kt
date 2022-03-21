package com.realtomjoney.pyxlmoose.activities.canvas

import android.os.Bundle
import com.realtomjoney.pyxlmoose.converters.BitmapConverter
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun CanvasActivity.extendedOnSaveInstanceState(outState: Bundle) {
    outState.putInt(StringConstants.prevOrientationBundleIdentifier, resources.configuration.orientation)
    outState.putString(StringConstants.prevBitmapStrBundleIdentifier, BitmapConverter.convertBitmapToString(outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap))
}