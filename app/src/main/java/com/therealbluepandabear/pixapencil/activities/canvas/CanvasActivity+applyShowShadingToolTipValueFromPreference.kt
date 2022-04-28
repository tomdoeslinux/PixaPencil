package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.utility.StringConstants

fun applyShowShadingToolTipValueFromPreference() {
    if (sharedPreferenceObject.contains(StringConstants.Identifiers.SharedPreferenceShowShadingToolTipIdentifier)) {
        sharedPreferenceShowShadingToolTip = sharedPreferenceObject.getBoolean(StringConstants.Identifiers.SharedPreferenceShowShadingToolTipIdentifier, true)
    }
}