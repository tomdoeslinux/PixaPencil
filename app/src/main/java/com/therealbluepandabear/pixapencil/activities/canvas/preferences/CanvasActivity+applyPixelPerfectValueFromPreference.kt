package com.therealbluepandabear.pixapencil.activities.canvas.preferences

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun CanvasActivity.applyPixelPerfectValueFromPreference() {
    if (sharedPreferenceObject.contains(StringConstants.Identifiers.SHARED_PREFERENCE_PIXEL_PERFECT_IDENTIFIER)) {
        pixelGridViewInstance.pixelPerfectMode = sharedPreferenceObject.getBoolean(StringConstants.Identifiers.SHARED_PREFERENCE_PIXEL_PERFECT_IDENTIFIER, false)
    }
}
