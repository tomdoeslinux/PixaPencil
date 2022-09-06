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

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.databinding.BrushesLayoutBinding
import com.therealbluepandabear.pixapencil.listeners.BrushesListener
import com.therealbluepandabear.pixapencil.models.Brush
import com.therealbluepandabear.pixapencil.viewholders.BrushesViewHolder

class BrushesAdapter(
    private val data: List<Brush>,
    private val caller: BrushesListener,
    private val context: Context
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = BrushesLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return BrushesViewHolder(binding, context)
    }

    private var previousViewElement: View? = null

    private var defSelected = false

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]

        if (holder is BrushesViewHolder) {
            holder.bind(item)

            if (!defSelected) {
                holder.binding.brushesLayoutMaterialCardView.backgroundTintList = AppCompatResources.getColorStateList(context, R.color.recycler_view_item_tapped_color_daynight)
                previousViewElement = holder.binding.brushesLayoutMaterialCardView
                defSelected = true
            }

            holder.binding.brushesLayoutMaterialCardView.setOnClickListener {
                caller.onBrushTapped(item)

                previousViewElement?.backgroundTintList = AppCompatResources.getColorStateList(context, R.color.recycler_view_item_untapped_color_daynight)

                it.backgroundTintList = AppCompatResources.getColorStateList(context, R.color.recycler_view_item_tapped_color_daynight)
                previousViewElement = it
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}