package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.activities.canvas.preferences.applyGridEnabledValueFromPreference
import com.therealbluepandabear.pixapencil.activities.canvas.preferences.applyPixelPerfectValueFromPreference
import com.therealbluepandabear.pixapencil.activities.canvas.preferences.applyShowShadingToolTipValueFromPreference
import com.therealbluepandabear.pixapencil.activities.canvas.preferences.applyShowSprayToolTipValueFromPreference

fun CanvasActivity.extendedOnViewLoaded() {
    applyPixelPerfectValueFromPreference()
    applyGridEnabledValueFromPreference()
    applyShowShadingToolTipValueFromPreference()
    applyShowSprayToolTipValueFromPreference()
}