package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.canvashelpers.rotate
import com.therealbluepandabear.pixapencil.enums.RotationValue

fun CanvasActivity.onRotate90DegreesAntiClockwiseOptionsItemSelected() {
    rotate(RotationValue.NinetyAntiClockwise)
}