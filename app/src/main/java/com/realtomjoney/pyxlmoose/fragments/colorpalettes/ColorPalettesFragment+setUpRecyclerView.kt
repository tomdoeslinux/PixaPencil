package com.realtomjoney.pyxlmoose.fragments.colorpalettes

import androidx.recyclerview.widget.LinearLayoutManager
import com.realtomjoney.pyxlmoose.adapters.ColorPalettesAdapter
import com.realtomjoney.pyxlmoose.database.AppData

fun ColorPalettesFragment.setUpRecyclerView() {
    binding.apply {
        fragmentColorPalettesRecyclerView.layoutManager = LinearLayoutManager(activity).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }
        AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettes().observe(lifecycleOwner) {
            fragmentColorPalettesRecyclerView.adapter = ColorPalettesAdapter(it, context)
        }
    }
}