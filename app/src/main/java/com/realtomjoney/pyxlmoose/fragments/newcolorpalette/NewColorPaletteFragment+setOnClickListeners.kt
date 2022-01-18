package com.realtomjoney.pyxlmoose.fragments.newcolorpalette

fun setOnClickListeners() {
    binding.fragmentNewColorPaletteDoneButton.setOnClickListener {
        val extractColorPaletteFromCanvas = binding.fragmentNewColorPaletteExtractFromCanvasCheckBox.isChecked
        caller.onDoneButtonPressed(binding.fragmentNewColorPaletteColorPaletteNameTextInputEditText.text.toString(), extractColorPaletteFromCanvas)
    }
}