package com.therealbluepandabear.pixapencil.activities.canvas.preferences

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun CanvasActivity.applyShowDitherToolTipFromPreference() {
    if (sharedPreferenceObject.contains(StringConstants.Identifiers.SharedPreferenceShowDitherToolTipIdentifier)) {
        sharedPreferenceShowDitherToolTip = sharedPreferenceObject.getBoolean(StringConstants.Identifiers.SharedPreferenceShowDitherToolTipIdentifier, true)
    }
}