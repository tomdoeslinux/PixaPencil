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

package com.therealbluepandabear.pixapencil.fragments.dithertoolsettings

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.therealbluepandabear.pixapencil.adapters.DitherBrushesAdapter
import com.therealbluepandabear.pixapencil.database.DitherBrushDatabase

fun DitherToolSettingsFragment.setUpRecyclerView() {
    val layoutManager = GridLayoutManager(activity, 3)
    layoutManager.orientation = LinearLayoutManager.HORIZONTAL

    binding.fragmentDitherBrushesRecyclerView.layoutManager = layoutManager
    binding.fragmentDitherBrushesRecyclerView.adapter = DitherBrushesAdapter(DitherBrushDatabase.toList(), this, this@setUpRecyclerView.requireContext())
}