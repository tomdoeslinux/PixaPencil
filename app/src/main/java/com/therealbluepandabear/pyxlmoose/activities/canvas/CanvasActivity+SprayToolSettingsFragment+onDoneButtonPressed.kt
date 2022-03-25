package com.therealbluepandabear.pyxlmoose.activities.canvas

import com.therealbluepandabear.pyxlmoose.utility.IntConstants

fun CanvasActivity.extendedOnDoneButtonPressed(radius: String, strength: String) {
    IntConstants.SprayRadius = radius.toInt()
    IntConstants.SprayStrength = strength.toInt()

    navigateBack(sprayToolSettingsFragmentInstance)
}