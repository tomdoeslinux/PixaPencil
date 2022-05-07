package com.therealbluepandabear.pixapencil.activities.canvas.preferences

import com.therealbluepandabear.pixapencil.activities.canvas.sharedPreferenceObject
import com.therealbluepandabear.pixapencil.activities.canvas.sharedPreferenceShowShadingToolTip
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun applyShowShadingToolTipValueFromPreference() {
    if (sharedPreferenceObject.contains(StringConstants.Identifiers.SharedPreferenceShowShadingToolTipIdentifier)) {
        sharedPreferenceShowShadingToolTip = sharedPreferenceObject.getBoolean(StringConstants.Identifiers.SharedPreferenceShowShadingToolTipIdentifier, true)
    }
}