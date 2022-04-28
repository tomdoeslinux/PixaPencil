package com.therealbluepandabear.pixapencil.activities.canvas

fun CanvasActivity.extendedOnViewLoaded() {
    applyPixelPerfectValueFromPreference()
    applyGridEnabledValueFromPreference()
    applyShowShadingToolTipValueFromPreference()
    applyShowSprayToolTipValueFromPreference()
}