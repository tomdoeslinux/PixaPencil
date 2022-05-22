package com.therealbluepandabear.pixapencil.activities.canvas.preferences

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun CanvasActivity.applyShowShadingToolTipValueFromPreference() {
    if (sharedPreferenceObject.contains(StringConstants.Identifiers.SharedPreferenceShowShadingToolTipIdentifier)) {
        sharedPreferenceShowShadingToolTip = sharedPreferenceObject.getBoolean(StringConstants.Identifiers.SharedPreferenceShowShadingToolTipIdentifier, true)
    }
}