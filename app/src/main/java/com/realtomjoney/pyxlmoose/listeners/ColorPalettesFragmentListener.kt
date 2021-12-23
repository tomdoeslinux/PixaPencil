package com.realtomjoney.pyxlmoose.listeners

import com.realtomjoney.pyxlmoose.models.ColorPalette

// ✔ CLEAN

interface ColorPalettesFragmentListener {
    fun onColorPaletteTapped(selectedColorPalette: ColorPalette)
}