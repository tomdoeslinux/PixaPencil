package com.therealbluepandabear.pixapencil.activities.canvas

import android.os.Bundle
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun CanvasActivity.configureSavedInstanceState(savedInstanceState: Bundle?) {
    if (savedInstanceState != null) {
        prevOrientation = savedInstanceState.getInt(StringConstants.Identifiers.PrevOrientationBundleIdentifier)
        prevBitmapFilePathStr = savedInstanceState.getString(StringConstants.Identifiers.PrevBitmapFilePathBundleIdentifier)
        prevRotation = savedInstanceState.getInt(StringConstants.Identifiers.PrevRotationBundleIdentifier)
    }
}