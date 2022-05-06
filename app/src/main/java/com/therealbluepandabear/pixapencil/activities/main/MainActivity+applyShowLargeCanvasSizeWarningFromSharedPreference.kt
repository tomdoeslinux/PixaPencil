package com.therealbluepandabear.pixapencil.activities.main

import com.therealbluepandabear.pixapencil.utility.StringConstants

fun MainActivity.applyShowLargeCanvasSizeWarningValueFromPreference() {
    if (sharedPreferenceObject.contains(StringConstants.Identifiers.SharedPreferenceShowLargeCanvasSizeWarningIdentifier)) {
        showLargeCanvasSizeWarning = sharedPreferenceObject.getBoolean(StringConstants.Identifiers.SharedPreferenceShowLargeCanvasSizeWarningIdentifier, false)
    }
}