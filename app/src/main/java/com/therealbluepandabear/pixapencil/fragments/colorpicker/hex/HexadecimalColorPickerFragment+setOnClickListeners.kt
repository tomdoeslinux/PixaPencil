/*
 * PixaPencil
 * Copyright 2022  therealbluepandabear
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.therealbluepandabear.pixapencil.fragments.colorpicker.hex

import android.graphics.drawable.ColorDrawable
import android.os.Build
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.enums.OutputCode
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.extensions.copyToClipboard
import com.therealbluepandabear.pixapencil.extensions.showSnackbar
import com.therealbluepandabear.pixapencil.fragments.colorpicker.caller
import com.therealbluepandabear.pixapencil.fragments.colorpicker.colorPalette

fun HexadecimalColorPickerFragment.setOnClickListeners() {
    binding.fragmentHexadecimalColorPickerHexadecimalValueTextInputLayout.setEndIconOnClickListener {
        val code = this@setOnClickListeners.requireContext().copyToClipboard("hex_code", binding.fragmentHexadecimalColorPickerHexadecimalValueTextInputEditText.text.toString())

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
            if (code == OutputCode.Success) {
                binding.fragmentHexadecimalColorPickerCoordinatorLayout.showSnackbar(
                    getString(R.string.fragmentHexadecimalColorPicker_successfully_copied_to_clipboard),
                    SnackbarDuration.Short
                )
            } else {
                binding.fragmentHexadecimalColorPickerCoordinatorLayout.showSnackbar(
                    getString(R.string.fragmentHexadecimalColorPicker_failed_to_copy_to_clipboard),
                    SnackbarDuration.Short
                )
            }
        }
    }

    binding.fragmentHexadecimalColorPickerDoneButton.setOnClickListener {
        try {
            caller.onDoneButtonPressed(
                (binding.fragmentHexadecimalColorPickerColorPreview.background as ColorDrawable).color,
                colorPalette
            )
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }
}