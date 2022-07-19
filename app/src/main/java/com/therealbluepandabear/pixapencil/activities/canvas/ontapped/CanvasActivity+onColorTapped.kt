package com.therealbluepandabear.pixapencil.activities.canvas.ontapped

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.setPixelColor

fun CanvasActivity.extendedOnColorTapped(color: Int) {
    setPixelColor(color)
}