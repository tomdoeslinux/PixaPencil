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
import android.graphics.Bitmap
import android.graphics.Color
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.scale
import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.database.ColorDatabase
import com.therealbluepandabear.pixapencil.databinding.BrushesLayoutBinding
import com.therealbluepandabear.pixapencil.extensions.setPixel
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.models.DitherBrush


class DitherBrushesViewHolder(val binding: BrushesLayoutBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {
    fun bind(ditherBrush: DitherBrush) {
        binding.brushesLayoutMaterialCardView.backgroundTintList = AppCompatResources.getColorStateList(context, R.color.recycler_view_item_untapped_color_daynight)
        binding.brushesLayoutMaterialCardView.layoutParams.width = 200
        binding.brushesLayoutMaterialCardView.layoutParams.height = 200

        val ditherBrushPreviewBitmap = Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888)

        for (i_1 in 0..9) {
            for (i_2 in 0..9) {
                if (ditherBrush.algorithm.invoke(Coordinates(i_1, i_2))) {
                    ditherBrushPreviewBitmap.setPixel(Coordinates(i_1, i_2), ColorDatabase.toList().first())
                } else {
                    ditherBrushPreviewBitmap.setPixel(Coordinates(i_1, i_2), Color.WHITE)
                }
            }
        }

        val img = ImageView(context)
        img.setImageBitmap(ditherBrushPreviewBitmap.scale(200, 200, false))

        binding.brushesLayoutMaterialCardView.addView(img)
    }
}