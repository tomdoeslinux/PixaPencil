package com.realtomjoney.pyxlmoose.activities.canvas

import android.os.Bundle
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun CanvasActivity.configureSavedInstanceState(savedInstanceState: Bundle?) {
    if (savedInstanceState != null) {
        prevOrientation = savedInstanceState.getInt(StringConstants.prevOrientationBundleIdentifier)
        prevBitmapStr = savedInstanceState.getString(StringConstants.prevBitmapStrBundleIdentifier)!!
    }
}