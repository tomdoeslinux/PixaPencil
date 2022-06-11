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