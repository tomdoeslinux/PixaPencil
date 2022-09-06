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

package com.therealbluepandabear.pixapencil.fragments.colorpicker

import android.graphics.Color
import com.therealbluepandabear.pixapencil.databinding.FragmentColorPickerBinding
import com.therealbluepandabear.pixapencil.listeners.ColorPickerFragmentListener
import com.therealbluepandabear.pixapencil.models.ColorPalette

var _binding: FragmentColorPickerBinding? = null

var oldColor_ = Color.BLACK
var colorPalette: ColorPalette? = null

val binding get(): FragmentColorPickerBinding {
    return _binding!!
}

lateinit var caller: ColorPickerFragmentListener

var prevColorPickerTab: Int = 0
