package com.therealbluepandabear.pixapencil.fragments.colorpicker.hex

import android.graphics.drawable.ColorDrawable
import com.therealbluepandabear.pixapencil.fragments.colorpicker.caller
import com.therealbluepandabear.pixapencil.fragments.colorpicker.colorPalette
import com.therealbluepandabear.pixapencil.fragments.colorpicker.colorPaletteMode_

fun HexadecimalColorPickerFragment.setOnClickListeners() {
    binding.fragmentHexadecimalColorPickerDoneButton.setOnClickListener {
        try {
            caller.onDoneButtonPressed(
                (binding.fragmentHexadecimalColorPickerColorPreview.background as ColorDrawable).color,
                colorPalette
            )
        } catch (exception: Exception) { }
    }
}