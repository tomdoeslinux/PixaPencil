package com.therealbluepandabear.pixapencil.fragments.newcolorpalette

import com.therealbluepandabear.pixapencil.extensions.hideKeyboard

fun NewColorPaletteFragment.setOnClickListeners() {
    binding.fragmentNewColorPaletteDoneButton.setOnClickListener {
        hideKeyboard()
        val extractColorPaletteFromCanvasCheckboxState = binding.fragmentNewColorPaletteExtractFromCanvasCheckBox.isChecked
        caller.onDoneButtonPressed(binding.fragmentNewColorPaletteColorPaletteNameTextInputEditText.text.toString(), extractColorPaletteFromCanvasCheckboxState)
    }
}