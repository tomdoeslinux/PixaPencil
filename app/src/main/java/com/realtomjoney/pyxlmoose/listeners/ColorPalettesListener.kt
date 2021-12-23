package com.realtomjoney.pyxlmoose.listeners

import com.realtomjoney.pyxlmoose.models.ColorPalette

// ✔ CLEAN

interface ColorPalettesListener {
    fun onColorPaletteTapped(selectedColorPalette: ColorPalette)
    fun onColorPaletteLongTapped(selectedColorPalette: ColorPalette)
}