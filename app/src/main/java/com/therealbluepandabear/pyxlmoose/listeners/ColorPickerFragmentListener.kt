package com.therealbluepandabear.pyxlmoose.listeners

interface ColorPickerFragmentListener {
    fun onDoneButtonPressed(selectedColor: Int, isColorPaletteMode: Boolean = false)
}