package com.therealbluepandabear.pixapencil.fragments.colorpicker.hex

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.drawable.ColorDrawable
import com.therealbluepandabear.pixapencil.enums.OutputCode
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.extensions.copyToClipboard
import com.therealbluepandabear.pixapencil.extensions.showSnackbar
import com.therealbluepandabear.pixapencil.fragments.colorpicker.caller
import com.therealbluepandabear.pixapencil.fragments.colorpicker.colorPalette

fun HexadecimalColorPickerFragment.setOnClickListeners() {
    binding.fragmentHexadecimalColorPickerHexadecimalValueTextInputLayout.setEndIconOnClickListener {
        val code = this@setOnClickListeners.requireContext().copyToClipboard("hex_code", binding.fragmentHexadecimalColorPickerHexadecimalValueTextInputEditText.text.toString())

        if (code == OutputCode.Success) {
            binding.fragmentHexadecimalColorPickerCoordinatorLayout.showSnackbar("Successfully copied to clipboard", SnackbarDuration.Short)
        } else {
            binding.fragmentHexadecimalColorPickerCoordinatorLayout.showSnackbar("Failed to copy text clipboard", SnackbarDuration.Short)
        }
    }

    binding.fragmentHexadecimalColorPickerDoneButton.setOnClickListener {
        try {
            caller.onDoneButtonPressed(
                (binding.fragmentHexadecimalColorPickerColorPreview.background as ColorDrawable).color,
                colorPalette
            )
        } catch (exception: Exception) { }
    }
}