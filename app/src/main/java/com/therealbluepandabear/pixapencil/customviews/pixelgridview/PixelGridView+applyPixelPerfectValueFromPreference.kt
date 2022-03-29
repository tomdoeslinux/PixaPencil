package com.therealbluepandabear.pixapencil.customviews.pixelgridview

import com.therealbluepandabear.pixapencil.activities.canvas.outerCanvasInstance
import com.therealbluepandabear.pixapencil.activities.canvas.sharedPreferenceObject
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun extendedApplyPixelPerfectValueFromPreference() {
    if (sharedPreferenceObject.contains(StringConstants.SharedPreferencePixelPerfectIdentifier)) {
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelPerfectMode = sharedPreferenceObject.getBoolean(
            StringConstants.SharedPreferencePixelPerfectIdentifier, false)
    }
}
