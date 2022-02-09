package com.realtomjoney.pyxlmoose.fragments.brushes

import androidx.recyclerview.widget.LinearLayoutManager
import com.realtomjoney.pyxlmoose.adapters.BrushesAdapter
import com.realtomjoney.pyxlmoose.database.BrushesDatabase

fun BrushesFragment.setUpRecyclerView() {
    binding.fragmentBrushesRecyclerView.layoutManager = LinearLayoutManager(activity).apply {
        orientation = LinearLayoutManager.HORIZONTAL
    }
    binding.fragmentBrushesRecyclerView.adapter = BrushesAdapter(BrushesDatabase.toList(), this)
}