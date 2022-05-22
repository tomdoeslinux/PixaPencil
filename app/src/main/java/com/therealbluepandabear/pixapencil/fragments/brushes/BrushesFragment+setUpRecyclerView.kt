package com.therealbluepandabear.pixapencil.fragments.brushes

import android.content.res.Configuration
import androidx.recyclerview.widget.LinearLayoutManager
import com.therealbluepandabear.pixapencil.adapters.BrushesAdapter
import com.therealbluepandabear.pixapencil.database.BrushesDatabase

fun BrushesFragment.setUpRecyclerView() {
    val layoutManager = LinearLayoutManager(activity)

    val layoutManagerOrientation: Int = if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
        LinearLayoutManager.HORIZONTAL
    } else {
        LinearLayoutManager.VERTICAL
    }

    layoutManager.orientation = layoutManagerOrientation

    binding.fragmentBrushesRecyclerView.layoutManager = layoutManager

    binding.fragmentBrushesRecyclerView.adapter = BrushesAdapter(BrushesDatabase.toList(), this, this@setUpRecyclerView.requireContext())
}