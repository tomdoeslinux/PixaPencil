package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.utility.StringConstants

fun applyShowSprayToolTipValueFromPreference() {
    if (sharedPreferenceObject.contains(StringConstants.Identifiers.SharedPreferenceShowSprayToolTipIdentifier)) {
        sharedPreferenceShowSprayToolTip = sharedPreferenceObject.getBoolean(StringConstants.Identifiers.SharedPreferenceShowSprayToolTipIdentifier, true)
    }
}