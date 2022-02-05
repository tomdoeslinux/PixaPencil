package com.realtomjoney.pyxlmoose.customviews.pixelgridview

import com.realtomjoney.pyxlmoose.activities.canvas.outerCanvasInstance
import com.realtomjoney.pyxlmoose.activities.canvas.sharedPreferenceObject
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun extendedApplyPixelPerfectValueFromPreference() {
    if (sharedPreferenceObject.contains(StringConstants.SHARED_PREF_PIXEL_PERFECT)) {
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelPerfectMode = sharedPreferenceObject.getBoolean(
            StringConstants.SHARED_PREF_PIXEL_PERFECT, false)
    }
}
