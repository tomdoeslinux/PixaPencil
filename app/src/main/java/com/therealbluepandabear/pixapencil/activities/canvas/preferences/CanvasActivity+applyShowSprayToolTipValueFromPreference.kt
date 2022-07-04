package com.therealbluepandabear.pixapencil.activities.canvas.preferences

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun CanvasActivity.applyShowSprayToolTipValueFromPreference() {
    if (sharedPreferenceObject.contains(StringConstants.Identifiers.SHARED_PREFERENCE_SHOW_SPRAY_TOOLTIP_IDENTIFIER)) {
        sharedPreferenceShowSprayToolTip = sharedPreferenceObject.getBoolean(StringConstants.Identifiers.SHARED_PREFERENCE_SHOW_SPRAY_TOOLTIP_IDENTIFIER, true)
    }
}