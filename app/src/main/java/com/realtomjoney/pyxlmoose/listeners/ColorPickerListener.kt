package com.realtomjoney.pyxlmoose.listeners

import android.view.View
import com.realtomjoney.pyxlmoose.models.ColorPalette

// ✔ CLEAN

interface ColorPickerListener {
    fun onColorTapped(colorTapped: Int, view: View)
    fun onColorAdded(colorPalette: ColorPalette)
}