package com.therealbluepandabear.pixapencil.listeners

import android.view.View
import com.therealbluepandabear.pixapencil.models.ColorPalette

interface ColorPickerListener {
    fun onColorTapped(colorTapped: Int, view: View)
    fun onColorLongTapped(colorPalette: ColorPalette, colorIndex: Int)
    fun onColorAdded(colorPalette: ColorPalette)
}