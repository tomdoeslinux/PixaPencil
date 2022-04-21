package com.therealbluepandabear.pixapencil.customviews.pixelgridview

import com.therealbluepandabear.pixapencil.activities.canvas.sharedPreferenceObject
import com.therealbluepandabear.pixapencil.utility.Flags
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun PixelGridView.applyGridEnabledValueFromPreference() {
    if (sharedPreferenceObject.contains(StringConstants.Identifiers.SharedPreferenceGridIdentifier) && !Flags.DisableGridSharedPreferenceChanges) {
        gridEnabled = sharedPreferenceObject.getBoolean(StringConstants.Identifiers.SharedPreferenceGridIdentifier, false)
    }
}