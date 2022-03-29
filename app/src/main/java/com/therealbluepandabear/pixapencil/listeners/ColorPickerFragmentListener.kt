package com.therealbluepandabear.pixapencil.listeners

interface ColorPickerFragmentListener {
    fun onDoneButtonPressed(selectedColor: Int, isColorPaletteMode: Boolean = false)
}