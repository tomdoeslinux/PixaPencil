package com.therealbluepandabear.pyxlmoose.listeners

import com.therealbluepandabear.pyxlmoose.models.ColorPalette

interface ColorPalettesFragmentListener {
    fun onColorPaletteTapped(selectedColorPalette: ColorPalette)
}