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

package com.therealbluepandabear.pixapencil.fragments.newcolorpalette

import com.therealbluepandabear.pixapencil.R

fun NewColorPaletteFragment.setOnClickListeners() {
    binding.fragmentNewColorPaletteDoneButton.setOnClickListener {
        val extractColorPaletteFromCanvasCheckboxState = binding.fragmentNewColorPaletteExtractFromCanvasCheckBox.isChecked

        if (binding.fragmentNewColorPaletteColorPaletteNameTextInputEditText.text.toString().isEmpty()) {
            binding.fragmentNewColorPaletteColorPaletteNameTextInputLayout.error = getString(R.string.exception_empty_color_palette_name)
        } else {
            caller.onDoneButtonPressed(binding.fragmentNewColorPaletteColorPaletteNameTextInputEditText.text.toString(), extractColorPaletteFromCanvasCheckboxState)
        }
    }
}