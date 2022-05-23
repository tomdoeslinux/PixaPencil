package com.therealbluepandabear.pixapencil.extensions

import android.graphics.Color
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.customviews.pixelgridview.extendedCoordinatesInCanvasBounds
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun MenuItem.enable() {
    isEnabled = true
    icon.alpha = 255
}

fun MenuItem.disable() {
    isEnabled = false
    icon.alpha = 180
}