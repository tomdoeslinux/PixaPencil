package com.realtomjoney.pyxlmoose.listeners

import com.realtomjoney.pyxlmoose.models.ColorPalette

interface ColorPalettesFragmentListener {
    fun onColorPaletteTapped(selectedColorPalette: ColorPalette)
}