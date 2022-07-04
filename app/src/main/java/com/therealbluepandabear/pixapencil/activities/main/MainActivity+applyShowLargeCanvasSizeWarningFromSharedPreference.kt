package com.therealbluepandabear.pixapencil.activities.main

import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun MainActivity.applyShowLargeCanvasSizeWarningValueFromPreference() {
    if (sharedPreferenceObject.contains(StringConstants.Identifiers.SHARED_PREFERENCE_SHOW_LARGE_CANVAS_SIZE_WARNING_IDENTIFIER)) {
        showLargeCanvasSizeWarning = sharedPreferenceObject.getBoolean(StringConstants.Identifiers.SHARED_PREFERENCE_SHOW_LARGE_CANVAS_SIZE_WARNING_IDENTIFIER, false)
    }
}