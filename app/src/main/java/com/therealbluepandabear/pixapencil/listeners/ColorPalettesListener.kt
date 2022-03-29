package com.therealbluepandabear.pixapencil.listeners

import com.therealbluepandabear.pixapencil.models.ColorPalette

interface ColorPalettesListener {
    fun onColorPaletteTapped(selectedColorPalette: ColorPalette)
    fun onColorPaletteLongTapped(selectedColorPalette: ColorPalette)
}