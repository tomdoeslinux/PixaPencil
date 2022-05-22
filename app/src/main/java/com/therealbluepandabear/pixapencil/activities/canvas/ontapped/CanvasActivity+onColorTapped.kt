package com.therealbluepandabear.pixapencil.activities.canvas.ontapped

import android.view.View
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.setPixelColor
import com.therealbluepandabear.pixapencil.activities.canvas.updateColorSelectedIndicator

fun CanvasActivity.extendedOnColorTapped(color: Int, it: View) {
    setPixelColor(color)

    isSelected = if (!isSelected) {
        updateColorSelectedIndicator(it)
        true
    } else {
        updateColorSelectedIndicator(it)
        false
    }
}