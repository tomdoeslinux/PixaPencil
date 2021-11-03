package com.realtomjoney.pyxlmoose.activities.canvas

import androidx.recyclerview.widget.LinearLayoutManager
import com.realtomjoney.pyxlmoose.database.ColourDatabase
import com.realtomjoney.pyxlmoose.adapters.ColourPickerAdapter

fun CanvasActivity.extendedSetUpRecyclerView() {
    val layoutManager = LinearLayoutManager(this)
    layoutManager.orientation = LinearLayoutManager.HORIZONTAL
    binding.colourPickerRecyclerView.layoutManager = layoutManager
    binding.colourPickerRecyclerView.adapter = ColourPickerAdapter(ColourDatabase.toList(), this)
}