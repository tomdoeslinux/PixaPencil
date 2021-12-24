package com.realtomjoney.pyxlmoose.fragments.newcolorpalette

fun NewColorPaletteFragment.setOnClickListeners() {
    binding.fragmentNewColorPaletteDoneButton.setOnClickListener {
        caller.onDoneButtonPressed(binding.fragmentNewColorPaletteColorPaletteNameTextInputEditText.text.toString())
    }
}