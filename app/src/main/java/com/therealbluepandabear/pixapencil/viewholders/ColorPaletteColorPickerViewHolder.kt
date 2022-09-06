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

package com.therealbluepandabear.pixapencil.viewholders

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.databinding.ColorPickerLayoutBinding

class ColorPaletteColorPickerViewHolder(val binding: ColorPickerLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(colorData: List<Int>, position: Int, isPlusIndicatorItemPosition: Boolean) {
        binding.colorView.backgroundTintList = ColorStateList.valueOf(colorData[position])

        if (isPlusIndicatorItemPosition) {
            binding.colorView.setBackgroundResource(R.drawable.ic_baseline_add_24)
            binding.colorView.background.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(Color.GRAY, BlendModeCompat.DST_OVER)
        }
    }
}