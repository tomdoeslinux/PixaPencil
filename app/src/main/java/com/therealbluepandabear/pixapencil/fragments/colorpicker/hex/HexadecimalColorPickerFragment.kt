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

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.therealbluepandabear.pixapencil.databinding.FragmentHexadecimalColorPickerBinding
import com.therealbluepandabear.pixapencil.fragments.colorpicker.oldColor_

class HexadecimalColorPickerFragment : Fragment() {
    private var _binding: FragmentHexadecimalColorPickerBinding? = null

    val binding get(): FragmentHexadecimalColorPickerBinding {
        return _binding!!
    }

    private val oldColorAsHex = Integer.toHexString(oldColor_)

    private fun setup() {
        binding.fragmentHexadecimalColorPickerColorPreview.setBackgroundColor(oldColor_)
        binding.fragmentHexadecimalColorPickerHexadecimalValueTextInputEditText.setText(oldColorAsHex)
    }

    private fun setDoAfterTextChangedListeners() {
        binding.fragmentHexadecimalColorPickerHexadecimalValueTextInputEditText.doAfterTextChanged {
            try {
                val text = binding.fragmentHexadecimalColorPickerHexadecimalValueTextInputEditText.text.toString().replace("#", "")
                val color = Color.parseColor("#$text")
                binding.fragmentHexadecimalColorPickerColorPreview.setBackgroundColor(color)
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

    companion object {
        fun newInstance(): HexadecimalColorPickerFragment {
            return HexadecimalColorPickerFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHexadecimalColorPickerBinding.inflate(inflater, container, false)

        setOnClickListeners()
        setup()
        setDoAfterTextChangedListeners()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}