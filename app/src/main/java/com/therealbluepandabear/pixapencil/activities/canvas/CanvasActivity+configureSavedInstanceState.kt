package com.therealbluepandabear.pixapencil.activities.canvas

import android.os.Bundle
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun configureSavedInstanceState(savedInstanceState: Bundle?) {
    if (savedInstanceState != null) {
        prevOrientation = savedInstanceState.getInt(StringConstants.prevOrientationBundleIdentifier)
        prevBitmapStr = savedInstanceState.getString(StringConstants.prevBitmapStrBundleIdentifier)!!
    }
}