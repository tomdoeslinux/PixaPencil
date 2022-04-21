package com.therealbluepandabear.pixapencil.customviews.pixelgridview

import com.therealbluepandabear.pixapencil.activities.canvas.sharedPreferenceObject
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun PixelGridView.applyPixelPerfectValueFromPreference() {
    if (sharedPreferenceObject.contains(StringConstants.Identifiers.SharedPreferencePixelPerfectIdentifier)) {
        pixelPerfectMode = sharedPreferenceObject.getBoolean(StringConstants.Identifiers.SharedPreferencePixelPerfectIdentifier, false)
    }
}
