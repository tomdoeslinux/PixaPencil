package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.utility.IntConstants

fun CanvasActivity.extendedOnDoneButtonPressed(radius: String, strength: String) {
    IntConstants.SprayRadius = radius.toInt()
    IntConstants.SprayStrength = strength.toInt()

    navigateBack(sprayToolSettingsFragmentInstance)
}