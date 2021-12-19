package com.realtomjoney.pyxlmoose.listeners

import android.view.View

interface ColorPickerListener {
    fun onColorTapped(selectedColor: Int, it: View)
}