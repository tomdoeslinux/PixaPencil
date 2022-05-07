package com.therealbluepandabear.pixapencil.activities.canvas.preferences

import com.therealbluepandabear.pixapencil.activities.canvas.sharedPreferenceObject
import com.therealbluepandabear.pixapencil.activities.canvas.sharedPreferenceShowSprayToolTip
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun applyShowSprayToolTipValueFromPreference() {
    if (sharedPreferenceObject.contains(StringConstants.Identifiers.SharedPreferenceShowSprayToolTipIdentifier)) {
        sharedPreferenceShowSprayToolTip = sharedPreferenceObject.getBoolean(StringConstants.Identifiers.SharedPreferenceShowSprayToolTipIdentifier, true)
    }
}