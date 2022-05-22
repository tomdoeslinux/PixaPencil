package com.therealbluepandabear.pixapencil.listeners

import android.view.View

interface ColorPickerListener {
    fun onColorTapped(colorTapped: Int, view: View)
}