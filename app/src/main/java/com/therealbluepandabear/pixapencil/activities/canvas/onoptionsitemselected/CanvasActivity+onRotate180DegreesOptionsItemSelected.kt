package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.utility.constants.IntConstants

fun CanvasActivity.onRotate180DegreesOptionsItemSelected() {
    outerCanvasInstance.rotate(IntConstants.DegreesOneEighty)
}