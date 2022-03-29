package com.therealbluepandabear.pixapencil.listeners

import com.therealbluepandabear.pixapencil.models.ColorPalette

interface ColorPalettesFragmentListener {
    fun onColorPaletteTapped(selectedColorPalette: ColorPalette)
}