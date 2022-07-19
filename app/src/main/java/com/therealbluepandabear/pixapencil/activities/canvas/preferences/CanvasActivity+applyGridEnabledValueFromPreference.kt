package com.therealbluepandabear.pixapencil.activities.canvas.preferences

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.utility.constants.Flags
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun CanvasActivity.applyGridEnabledValueFromPreference() {
    if (sharedPreferenceObject.contains(StringConstants.Identifiers.SHARED_PREFERENCE_GRID_IDENTIFIER) && !Flags.DisableGridSharedPreferenceChanges) {
        binding.activityCanvasPixelGridView.gridEnabled = sharedPreferenceObject.getBoolean(StringConstants.Identifiers.SHARED_PREFERENCE_GRID_IDENTIFIER, false)
    }
}