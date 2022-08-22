package com.therealbluepandabear.pixapencil.fragments.colorpicker.rgb

import android.graphics.drawable.ColorDrawable
import com.therealbluepandabear.pixapencil.fragments.colorpicker.caller
import com.therealbluepandabear.pixapencil.fragments.colorpicker.colorPalette

fun RGBColorPickerFragment.setOnClickListeners() {
    binding.fragmentRGBColorPickerDoneButton.setOnClickListener {
        try {
            caller.onDoneButtonPressed((binding.fragmentRGBColorPickerColorPreview.background as ColorDrawable).color, colorPalette)
            unregisterKeyboardVisibilityEventListenerRegistrar()
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }
}