package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.utility.constants.IntConstants

fun CanvasActivity.onRotate90DegreesAntiClockwiseOptionsItemSelected() {
    outerCanvasInstance.rotate(IntConstants.DegreesNinety, animate = true, clockwise = false)
}