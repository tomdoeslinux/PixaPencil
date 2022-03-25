package com.therealbluepandabear.pyxlmoose.extensions

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable

fun Drawable.changeColor(color: Int) {
    colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)
}