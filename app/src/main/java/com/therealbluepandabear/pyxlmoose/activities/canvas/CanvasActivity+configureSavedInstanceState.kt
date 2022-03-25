package com.therealbluepandabear.pyxlmoose.activities.canvas

import android.os.Bundle
import com.therealbluepandabear.pyxlmoose.utility.StringConstants

fun configureSavedInstanceState(savedInstanceState: Bundle?) {
    if (savedInstanceState != null) {
        prevOrientation = savedInstanceState.getInt(StringConstants.prevOrientationBundleIdentifier)
        prevBitmapStr = savedInstanceState.getString(StringConstants.prevBitmapStrBundleIdentifier)!!
    }
}