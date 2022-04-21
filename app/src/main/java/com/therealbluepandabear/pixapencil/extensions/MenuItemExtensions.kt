package com.therealbluepandabear.pixapencil.extensions

import android.graphics.Color
import android.view.MenuItem
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun MenuItem.enable() {
    isEnabled = true
    icon.alpha = 255
    icon.changeColor(Color.parseColor(StringConstants.Colors.DefaultToolbarItemColor))
}

fun MenuItem.disable() {
    isEnabled = false
    icon.alpha = 180
}