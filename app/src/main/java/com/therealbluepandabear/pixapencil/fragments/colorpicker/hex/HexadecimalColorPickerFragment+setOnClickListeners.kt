package com.therealbluepandabear.pixapencil.fragments.colorpicker.hex

import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.os.Looper
import com.therealbluepandabear.pixapencil.activities.canvas.showMenuItems
import com.therealbluepandabear.pixapencil.extensions.hideKeyboard
import com.therealbluepandabear.pixapencil.fragments.colorpicker.caller
import com.therealbluepandabear.pixapencil.fragments.colorpicker.colorPaletteMode_
import com.therealbluepandabear.pixapencil.utility.LongConstants

fun HexadecimalColorPickerFragment.setOnClickListeners() {
    binding.fragmentRGBColorPickerDoneButton.setOnClickListener {
        hideKeyboard()
        Handler(Looper.getMainLooper()).postDelayed({
            try {
                caller.onDoneButtonPressed((binding.fragmentHexadecimalColorPickerColorPreview.background as ColorDrawable).color, colorPaletteMode_)
            } catch (exception: Exception) {

            } finally {
                showMenuItems()
            }
        }, LongConstants.DefaultHandlerDelay)
    }
}