package com.therealbluepandabear.pyxlmoose.activities.canvas

import android.os.Bundle
import com.therealbluepandabear.pyxlmoose.converters.BitmapConverter
import com.therealbluepandabear.pyxlmoose.utility.StringConstants

fun CanvasActivity.extendedOnSaveInstanceState(outState: Bundle) {
    outState.putInt(StringConstants.prevOrientationBundleIdentifier, resources.configuration.orientation)
    outState.putString(StringConstants.prevBitmapStrBundleIdentifier, BitmapConverter.convertBitmapToString(outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap))
}