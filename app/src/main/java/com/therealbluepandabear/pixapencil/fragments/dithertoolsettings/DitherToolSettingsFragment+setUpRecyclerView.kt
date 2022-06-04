package com.therealbluepandabear.pixapencil.fragments.dithertoolsettings

import android.content.res.Configuration
import androidx.recyclerview.widget.LinearLayoutManager
import com.therealbluepandabear.pixapencil.adapters.DitherBrushesAdapter
import com.therealbluepandabear.pixapencil.database.DitherBrushDatabase

fun DitherToolSettingsFragment.setUpRecyclerView() {
    val layoutManager = LinearLayoutManager(activity)

    val layoutManagerOrientation: Int = if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
        LinearLayoutManager.HORIZONTAL
    } else {
        LinearLayoutManager.VERTICAL
    }

    layoutManager.orientation = layoutManagerOrientation

    binding.fragmentDitherBrushesRecyclerView.layoutManager = layoutManager
    binding.fragmentDitherBrushesRecyclerView.adapter = DitherBrushesAdapter(DitherBrushDatabase.toList(), this, this@setUpRecyclerView.requireContext())
}