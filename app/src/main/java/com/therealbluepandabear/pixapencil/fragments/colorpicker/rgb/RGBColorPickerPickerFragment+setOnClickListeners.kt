package com.therealbluepandabear.pixapencil.fragments.colorpicker.rgb

import android.graphics.drawable.ColorDrawable
import com.therealbluepandabear.pixapencil.fragments.colorpicker.caller
import com.therealbluepandabear.pixapencil.fragments.colorpicker.colorPaletteMode_

fun RGBColorPickerFragment.setOnClickListeners() {
    binding.fragmentRGBColorPickerDoneButton.setOnClickListener {
        try {
            caller.onDoneButtonPressed((binding.fragmentRGBColorPickerColorPreview.background as ColorDrawable).color, colorPaletteMode_)
            unregisterKeyboardVisibilityEventListenerRegistrar()
        } catch (exception: Exception) { }
    }
}