package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.enums.RotationValue

fun CanvasActivity.onRotate90DegreesOptionsItemSelected() {
    outerCanvasInstance.rotate(RotationValue.NinetyClockwise, animate = true)
}