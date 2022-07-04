package com.therealbluepandabear.pixapencil.activities.canvas

import android.os.Bundle
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun CanvasActivity.configureSavedInstanceState(savedInstanceState: Bundle?) {
    if (savedInstanceState != null) {
        prevOrientation = savedInstanceState.getInt(StringConstants.Identifiers.PREV_ORIENTATION_BUNDLE_IDENTIFIER)
        prevBitmapFilePathStr = savedInstanceState.getString(StringConstants.Identifiers.PREV_BITMAP_FILE_PATH_BUNDLE_IDENTIFIER)
        prevRotation = savedInstanceState.getInt(StringConstants.Identifiers.PREV_ROTATION_BUNDLE_IDENTIFIER)
    }
}