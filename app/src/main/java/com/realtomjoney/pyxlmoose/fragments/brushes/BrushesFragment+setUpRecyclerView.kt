package com.realtomjoney.pyxlmoose.fragments.brushes

import androidx.recyclerview.widget.LinearLayoutManager
import com.realtomjoney.pyxlmoose.adapters.BrushesAdapter
import com.realtomjoney.pyxlmoose.adapters.ColorPalettesAdapter
import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.database.BrushesDatabase
import com.realtomjoney.pyxlmoose.databinding.FragmentBrushesBinding
import com.realtomjoney.pyxlmoose.fragments.colorpalettes.ColorPalettesFragment
import com.realtomjoney.pyxlmoose.listeners.BrushesListener

fun BrushesFragment.setUpRecyclerView() {
    binding.fragmentBrushesRecyclerView.layoutManager = LinearLayoutManager(activity).apply {
        orientation = LinearLayoutManager.HORIZONTAL
    }
    binding.fragmentBrushesRecyclerView.adapter = BrushesAdapter(BrushesDatabase.toList(), this)
}