package com.therealbluepandabear.pixapencil.listeners

import com.therealbluepandabear.pixapencil.models.ColorPalette

interface ColorPickerFragmentListener {
    fun onDoneButtonPressed(selectedColor: Int, colorPalette: ColorPalette? = null)
}