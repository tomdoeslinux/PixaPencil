package com.therealbluepandabear.pixapencil.activities.canvas.preferences

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun CanvasActivity.applyShowSprayToolTipValueFromPreference() {
    if (sharedPreferenceObject.contains(StringConstants.Identifiers.SharedPreferenceShowSprayToolTipIdentifier)) {
        sharedPreferenceShowSprayToolTip = sharedPreferenceObject.getBoolean(StringConstants.Identifiers.SharedPreferenceShowSprayToolTipIdentifier, true)
    }
}