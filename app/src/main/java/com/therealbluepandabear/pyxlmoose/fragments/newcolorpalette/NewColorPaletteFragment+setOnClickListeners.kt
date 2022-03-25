package com.therealbluepandabear.pyxlmoose.fragments.newcolorpalette

fun setOnClickListeners() {
    binding.fragmentNewColorPaletteDoneButton.setOnClickListener {
        val extractColorPaletteFromCanvasCheckboxState = binding.fragmentNewColorPaletteExtractFromCanvasCheckBox.isChecked
        caller.onDoneButtonPressed(binding.fragmentNewColorPaletteColorPaletteNameTextInputEditText.text.toString(), extractColorPaletteFromCanvasCheckboxState)
    }
}