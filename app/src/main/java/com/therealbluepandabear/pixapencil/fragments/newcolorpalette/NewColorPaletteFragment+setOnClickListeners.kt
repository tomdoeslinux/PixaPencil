package com.therealbluepandabear.pixapencil.fragments.newcolorpalette

import com.therealbluepandabear.pixapencil.R

fun NewColorPaletteFragment.setOnClickListeners() {
    binding.fragmentNewColorPaletteDoneButton.setOnClickListener {
        val extractColorPaletteFromCanvasCheckboxState = binding.fragmentNewColorPaletteExtractFromCanvasCheckBox.isChecked

        if (binding.fragmentNewColorPaletteColorPaletteNameTextInputEditText.text.toString().isEmpty()) {
            binding.fragmentNewColorPaletteColorPaletteNameTextInputLayout.error = getString(R.string.exception_empty_color_palette_name)
        } else {
            caller.onDoneButtonPressed(binding.fragmentNewColorPaletteColorPaletteNameTextInputEditText.text.toString(), extractColorPaletteFromCanvasCheckboxState)
        }
    }
}