package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.activities.canvas.preferences.*

fun CanvasActivity.extendedOnViewLoaded() {
    applyPixelPerfectValueFromPreference()
    applyGridEnabledValueFromPreference()
    applyShowShadingToolTipValueFromPreference()
    applyShowSprayToolTipValueFromPreference()
    applyShowDitherToolTipFromPreference()
}