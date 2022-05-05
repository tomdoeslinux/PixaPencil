package com.therealbluepandabear.pixapencil.fragments.colorpicker.hex

import android.graphics.drawable.ColorDrawable
import androidx.lifecycle.lifecycleScope
import com.therealbluepandabear.pixapencil.extensions.hideKeyboard
import com.therealbluepandabear.pixapencil.extensions.showItems
import com.therealbluepandabear.pixapencil.fragments.colorpicker.caller
import com.therealbluepandabear.pixapencil.fragments.colorpicker.colorPaletteMode_
import com.therealbluepandabear.pixapencil.utility.LongConstants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun HexadecimalColorPickerFragment.setOnClickListeners() {
    binding.fragmentHexadecimalColorPickerDoneButton.setOnClickListener {
        hideKeyboard()

        lifecycleScope.launch {
            delay(LongConstants.DefaultHandlerDelay)
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
}