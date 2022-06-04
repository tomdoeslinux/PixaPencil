package com.therealbluepandabear.pixapencil.fragments.dithertoolsettings

import androidx.recyclerview.widget.LinearLayoutManager
import com.therealbluepandabear.pixapencil.adapters.DitherBrushesAdapter
import com.therealbluepandabear.pixapencil.database.DitherBrushDatabase

fun DitherToolSettingsFragment.setUpRecyclerView() {
    val layoutManager = LinearLayoutManager(activity)
    layoutManager.orientation = LinearLayoutManager.HORIZONTAL

    binding.fragmentDitherBrushesRecyclerView.layoutManager = layoutManager
    binding.fragmentDitherBrushesRecyclerView.adapter = DitherBrushesAdapter(DitherBrushDatabase.toList(), this, this@setUpRecyclerView.requireContext())
}