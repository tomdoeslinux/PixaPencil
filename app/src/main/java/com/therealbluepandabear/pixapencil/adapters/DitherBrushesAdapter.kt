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
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.databinding.BrushesLayoutBinding
import com.therealbluepandabear.pixapencil.listeners.DitherBrushesListener
import com.therealbluepandabear.pixapencil.models.DitherBrush
import com.therealbluepandabear.pixapencil.viewholders.DitherBrushesViewHolder

class DitherBrushesAdapter(
    private val data: List<DitherBrush>,
    private val caller: DitherBrushesListener,
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = BrushesLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return DitherBrushesViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]

        if (holder is DitherBrushesViewHolder) {
            holder.setIsRecyclable(false)
            holder.bind(item)

            holder.binding.brushesLayoutMaterialCardView.setOnClickListener {
                caller.onDitherBrushTapped(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}