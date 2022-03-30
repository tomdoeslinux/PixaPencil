package com.therealbluepandabear.pixapencil.fragments.colorpicker.picker

import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.os.Looper
import com.therealbluepandabear.pixapencil.activities.canvas.menu
import com.therealbluepandabear.pixapencil.extensions.hideKeyboard
import com.therealbluepandabear.pixapencil.extensions.showItems
import com.therealbluepandabear.pixapencil.fragments.colorpicker.caller
import com.therealbluepandabear.pixapencil.fragments.colorpicker.colorPaletteMode_
import com.therealbluepandabear.pixapencil.utility.LongConstants

fun ColorPickerPickerFragment.setOnClickListeners() {
    binding.fragmentColorPickerPickerDoneButton.setOnClickListener {
        hideKeyboard()
        Handler(Looper.getMainLooper()).postDelayed({
            try {
                caller.onDoneButtonPressed((binding.fragmentColorPickerPickerColorPreview.background as ColorDrawable).color, colorPaletteMode_)
            } catch (exception: Exception) {

            } finally {
                menu.showItems()
            }
        },  LongConstants.DefaultHandlerDelay)
    }
}