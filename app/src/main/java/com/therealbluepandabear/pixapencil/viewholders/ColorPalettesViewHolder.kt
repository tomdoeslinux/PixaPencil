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

import android.content.Context
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.databinding.ColorPalettesLayoutBinding
import com.therealbluepandabear.pixapencil.models.ColorPalette

class ColorPalettesViewHolder(val binding: ColorPalettesLayoutBinding, val context: Context) : RecyclerView.ViewHolder(binding.root) {
    fun bind(colorPalette: ColorPalette) {
        binding.colorPalettesLayoutMaterialCardView.backgroundTintList = AppCompatResources.getColorStateList(context, R.color.recycler_view_item_untapped_color_daynight)

        val colorPaletteColorData = JsonConverter.convertJsonStringToListOfInt(colorPalette.colorPaletteColorData).toMutableList()
        colorPaletteColorData.removeLast()

        if (colorPaletteColorData.size >= 2) {
            if (binding.colorPalettesLayoutSecondColorRoot.visibility == View.INVISIBLE) {
                binding.colorPalettesLayoutSecondColorRoot.visibility = View.VISIBLE
            }

            binding.colorPalettesLayoutFirstColor.setBackgroundColor(JsonConverter.convertJsonStringToListOfInt(colorPalette.colorPaletteColorData)[0])
            binding.colorPalettesLayoutSecondColor.setBackgroundColor(JsonConverter.convertJsonStringToListOfInt(colorPalette.colorPaletteColorData)[1])
        } else if (colorPaletteColorData.size == 1) {
            if (binding.colorPalettesLayoutFirstColorRoot.visibility == View.INVISIBLE) {
                binding.colorPalettesLayoutFirstColorRoot.visibility = View.VISIBLE
            }

            binding.colorPalettesLayoutFirstColor.setBackgroundColor(JsonConverter.convertJsonStringToListOfInt(colorPalette.colorPaletteColorData)[0])
            binding.colorPalettesLayoutSecondColorRoot.visibility = View.INVISIBLE
        } else {
            binding.colorPalettesLayoutFirstColorRoot.visibility = View.INVISIBLE
            binding.colorPalettesLayoutSecondColorRoot.visibility = View.INVISIBLE
        }

        binding.colorPalettesLayoutColorPaletteTitle?.text = colorPalette.colorPaletteName
    }
}