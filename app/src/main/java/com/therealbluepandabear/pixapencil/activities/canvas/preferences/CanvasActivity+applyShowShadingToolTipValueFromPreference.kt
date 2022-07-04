package com.therealbluepandabear.pixapencil.activities.canvas.preferences

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun CanvasActivity.applyShowShadingToolTipValueFromPreference() {
    if (sharedPreferenceObject.contains(StringConstants.Identifiers.SHARED_PREFERENCE_SHOW_SHADING_TOOLTIP_IDENTIFIER)) {
        sharedPreferenceShowShadingToolTip = sharedPreferenceObject.getBoolean(StringConstants.Identifiers.SHARED_PREFERENCE_SHOW_SHADING_TOOLTIP_IDENTIFIER, true)
    }
}