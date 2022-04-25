package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun applyPixelPerfectValueFromPreference() {
    if (sharedPreferenceObject.contains(StringConstants.Identifiers.SharedPreferencePixelPerfectIdentifier)) {
        pixelGridViewInstance.pixelPerfectMode = sharedPreferenceObject.getBoolean(StringConstants.Identifiers.SharedPreferencePixelPerfectIdentifier, false)
    }
}
