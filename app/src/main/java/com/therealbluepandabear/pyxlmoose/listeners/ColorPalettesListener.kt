package com.therealbluepandabear.pyxlmoose.listeners

import com.therealbluepandabear.pyxlmoose.models.ColorPalette

interface ColorPalettesListener {
    fun onColorPaletteTapped(selectedColorPalette: ColorPalette)
    fun onColorPaletteLongTapped(selectedColorPalette: ColorPalette)
}