package com.therealbluepandabear.pixapencil.extensions

import android.view.MenuItem

fun MenuItem.enable() {
    isEnabled = true
    icon?.alpha = 255
}

fun MenuItem.disable() {
    isEnabled = false
    icon?.alpha = 180
}