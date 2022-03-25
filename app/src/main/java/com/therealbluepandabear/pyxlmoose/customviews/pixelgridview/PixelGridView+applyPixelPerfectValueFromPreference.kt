package com.therealbluepandabear.pyxlmoose.customviews.pixelgridview

import com.therealbluepandabear.pyxlmoose.activities.canvas.outerCanvasInstance
import com.therealbluepandabear.pyxlmoose.activities.canvas.sharedPreferenceObject
import com.therealbluepandabear.pyxlmoose.utility.StringConstants

fun extendedApplyPixelPerfectValueFromPreference() {
    if (sharedPreferenceObject.contains(StringConstants.SharedPreferencePixelPerfectIdentifier)) {
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelPerfectMode = sharedPreferenceObject.getBoolean(
            StringConstants.SharedPreferencePixelPerfectIdentifier, false)
    }
}
