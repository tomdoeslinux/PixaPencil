package com.realtomjoney.pyxlmoose.extensions

import android.graphics.Color
import android.view.MenuItem
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun MenuItem.enable() {
    isEnabled = true
    icon.alpha = 255
    icon.changeColor(Color.parseColor(StringConstants.DefaultToolbarItemColor))
}

fun MenuItem.disable() {
    isEnabled = false
    icon.alpha = 180
}