package com.therealbluepandabear.pixapencil.fragments.colorpicker.picker

import com.therealbluepandabear.pixapencil.fragments.colorpicker.caller
import com.therealbluepandabear.pixapencil.fragments.colorpicker.colorPalette
import com.therealbluepandabear.pixapencil.fragments.colorpicker.colorPaletteMode_

fun ColorPickerPickerFragment.setOnClickListeners() {
    binding.fragmentColorPickerPickerColorPickerView.subscribe { color, _, _ ->
        selectedColor = color
        binding.fragmentColorPickerPickerColorPreview.setBackgroundColor(selectedColor)
    }

    binding.fragmentColorPickerPickerDoneButton.setOnClickListener {
        caller.onDoneButtonPressed(selectedColor, colorPalette)
    }
}