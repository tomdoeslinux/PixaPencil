package com.realtomjoney.pyxlmoose.listeners

import android.view.View
import com.realtomjoney.pyxlmoose.models.ColorPalette

interface ColorPickerListener {
    fun onColorTapped(colorTapped: Int, view: View)
    fun onColorLongTapped(colorPalette: ColorPalette, colorIndex: Int)
    fun onColorAdded(colorPalette: ColorPalette)
}