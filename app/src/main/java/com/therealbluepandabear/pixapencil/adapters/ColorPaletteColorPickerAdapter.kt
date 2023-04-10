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

package com.therealbluepandabear.pixapencil.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.converters.JSON
import com.therealbluepandabear.pixapencil.databinding.ColorPickerLayoutBinding
import com.therealbluepandabear.pixapencil.extensions.setOnLongPressListener
import com.therealbluepandabear.pixapencil.listeners.ColorPaletteColorPickerListener
import com.therealbluepandabear.pixapencil.models.ColorPalette
import com.therealbluepandabear.pixapencil.viewholders.ColorPaletteColorPickerViewHolder

class ColorPaletteColorPickerAdapter(
    private var colorPalette: ColorPalette,
    private val caller: ColorPaletteColorPickerListener
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ColorPickerLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return ColorPaletteColorPickerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ColorPaletteColorPickerViewHolder) {
            val colorData = JSON.stringToIntList(colorPalette.colorPaletteColorData).toMutableList()
            val isPlusIndicatorItemPosition = colorData[position] == Color.TRANSPARENT && position == colorData.size - 1

            holder.bind(colorData, position, isPlusIndicatorItemPosition)

            holder.binding.colorView.setOnClickListener {
                if (isPlusIndicatorItemPosition) {
                    caller.onColorAdded(colorPalette)
                } else {
                    caller.onColorTapped(colorData[position])
                }
            }

            holder.binding.colorView.setOnLongPressListener {
                if (!isPlusIndicatorItemPosition) {
                    caller.onColorLongTapped(colorPalette, position)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        val colorData = JSON.stringToIntList(colorPalette.colorPaletteColorData).toMutableList()
        return colorData.size
    }

    fun updateData(colorPalette: ColorPalette){
        this.colorPalette = colorPalette
        notifyDataSetChanged()
    }
}
