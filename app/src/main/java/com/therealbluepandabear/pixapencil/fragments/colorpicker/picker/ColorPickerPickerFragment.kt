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

package com.therealbluepandabear.pixapencil.fragments.colorpicker.picker

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.therealbluepandabear.pixapencil.databinding.FragmentColorPickerPickerBinding
import com.therealbluepandabear.pixapencil.fragments.colorpicker.oldColor_

/**
 * Suppressed because the code is working fine how it is.
 */

@SuppressLint("ClickableViewAccessibility")

class ColorPickerPickerFragment : Fragment() {
    private var _binding: FragmentColorPickerPickerBinding? = null

    val binding get(): FragmentColorPickerPickerBinding {
        return _binding!!
    }

    var selectedColor: Int = 0

    private fun setup() {
        binding.fragmentColorPickerPickerColorPickerView.setInitialColor(oldColor_)
        binding.fragmentColorPickerPickerColorPreview.setBackgroundColor(oldColor_)
        selectedColor = oldColor_
        setOnClickListeners()
    }

    companion object {
        fun newInstance(): ColorPickerPickerFragment {
            return ColorPickerPickerFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentColorPickerPickerBinding.inflate(inflater, container, false)

        setup()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}