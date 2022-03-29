package com.therealbluepandabear.pixapencil.listeners

interface NewColorPaletteFragmentListener {
    fun onDoneButtonPressed(colorPaletteTitle: String, extractColorPaletteFromCanvas: Boolean)
}