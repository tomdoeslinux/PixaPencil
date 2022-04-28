package com.therealbluepandabear.pixapencil.fragments.colorpicker.rgb

import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.os.Looper
import com.therealbluepandabear.pixapencil.activities.canvas.menu
import com.therealbluepandabear.pixapencil.extensions.hideKeyboard
import com.therealbluepandabear.pixapencil.extensions.showItems
import com.therealbluepandabear.pixapencil.fragments.colorpicker.caller
import com.therealbluepandabear.pixapencil.fragments.colorpicker.colorPaletteMode_
import com.therealbluepandabear.pixapencil.utility.LongConstants

fun RGBColorPickerFragment.setOnClickListeners() {
    binding.fragmentRGBColorPickerDoneButton.setOnClickListener {
        hideKeyboard()
        Handler(Looper.getMainLooper()).postDelayed({
            try {
                caller.onDoneButtonPressed((binding.fragmentRGBColorPickerColorPreview.background as ColorDrawable).color, colorPaletteMode_)
                unregisterKeyboardVisibilityEventListenerRegistrar()
            } catch (exception: Exception) {

            } finally {
                menu.showItems()
            }
        }, LongConstants.DefaultHandlerDelay)
    }
}