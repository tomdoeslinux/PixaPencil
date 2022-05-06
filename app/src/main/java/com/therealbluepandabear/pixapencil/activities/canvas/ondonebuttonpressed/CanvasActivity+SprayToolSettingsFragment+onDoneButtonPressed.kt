package com.therealbluepandabear.pixapencil.activities.canvas.ondonebuttonpressed

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.utility.IntConstants

fun CanvasActivity.extendedOnDoneButtonPressed(radius: String, strength: String) {
    supportFragmentManager.popBackStackImmediate()

    IntConstants.SprayRadius = radius.toInt()
    IntConstants.SprayStrength = strength.toInt()
}