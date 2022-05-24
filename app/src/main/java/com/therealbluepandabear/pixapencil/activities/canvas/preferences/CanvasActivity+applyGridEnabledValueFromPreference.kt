package com.therealbluepandabear.pixapencil.activities.canvas.preferences

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.utility.constants.Flags
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun CanvasActivity.applyGridEnabledValueFromPreference() {
    if (sharedPreferenceObject.contains(StringConstants.Identifiers.SharedPreferenceGridIdentifier) && !Flags.DisableGridSharedPreferenceChanges) {
        pixelGridViewInstance.gridEnabled = sharedPreferenceObject.getBoolean(StringConstants.Identifiers.SharedPreferenceGridIdentifier, false)
    }
}