package com.therealbluepandabear.pixapencil.fragments.colorpicker.hex

import android.graphics.drawable.ColorDrawable
import com.therealbluepandabear.pixapencil.extensions.showItems
import com.therealbluepandabear.pixapencil.fragments.colorpicker.caller
import com.therealbluepandabear.pixapencil.fragments.colorpicker.colorPaletteMode_

fun setOnClickListeners() {
    binding.fragmentHexadecimalColorPickerDoneButton.setOnClickListener {
        try {
            caller.onDoneButtonPressed(
                (binding.fragmentHexadecimalColorPickerColorPreview.background as ColorDrawable).color,
                colorPaletteMode_
            )
        } catch (exception: Exception) {

        } finally {
            com.therealbluepandabear.pixapencil.activities.canvas.menu.showItems()
        }
    }
}