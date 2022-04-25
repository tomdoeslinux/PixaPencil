package com.therealbluepandabear.pixapencil.customviews.pixelgridview

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.sharedPreferenceObject
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun CanvasActivity.applyPixelPerfectValueFromPreference() {
    if (sharedPreferenceObject.contains(StringConstants.Identifiers.SharedPreferencePixelPerfectIdentifier)) {
        pixelGridViewInstance.pixelPerfectMode = sharedPreferenceObject.getBoolean(StringConstants.Identifiers.SharedPreferencePixelPerfectIdentifier, false)
    }
}
