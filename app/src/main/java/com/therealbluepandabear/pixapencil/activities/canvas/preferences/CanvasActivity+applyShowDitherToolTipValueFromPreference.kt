package com.therealbluepandabear.pixapencil.activities.canvas.preferences

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun CanvasActivity.applyShowDitherToolTipFromPreference() {
    if (sharedPreferenceObject.contains(StringConstants.Identifiers.SHARED_PREFERENCE_SHOW_DITHER_TOOLTIP_IDENTIFIER)) {
        sharedPreferenceShowDitherToolTip = sharedPreferenceObject.getBoolean(StringConstants.Identifiers.SHARED_PREFERENCE_SHOW_DITHER_TOOLTIP_IDENTIFIER, true)
    }
}