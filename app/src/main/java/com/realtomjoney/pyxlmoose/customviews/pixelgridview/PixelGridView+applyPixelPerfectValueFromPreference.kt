package com.realtomjoney.pyxlmoose.customviews.pixelgridview

import com.realtomjoney.pyxlmoose.activities.canvas.outerCanvasInstance
import com.realtomjoney.pyxlmoose.activities.canvas.sharedPreferenceObject
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun extendedApplyPixelPerfectValueFromPreference() {
    if (sharedPreferenceObject.contains(StringConstants.SharedPreferencePixelPerfectIdentifier)) {
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelPerfectMode = sharedPreferenceObject.getBoolean(
            StringConstants.SharedPreferencePixelPerfectIdentifier, false)
    }
}
