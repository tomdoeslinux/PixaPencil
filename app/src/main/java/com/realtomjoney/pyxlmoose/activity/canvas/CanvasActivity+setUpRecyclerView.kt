package com.realtomjoney.pyxlmoose.activity.canvas

import androidx.recyclerview.widget.LinearLayoutManager
import com.realtomjoney.pyxlmoose.ColourDatabase
import com.realtomjoney.pyxlmoose.ColourPickerAdapter

fun CanvasActivity.extendedSetUpRecyclerView() {
    val layoutManager = LinearLayoutManager(this)
    layoutManager.orientation = LinearLayoutManager.HORIZONTAL
    binding.colourPickerRecyclerView.layoutManager = layoutManager
    binding.colourPickerRecyclerView.adapter = ColourPickerAdapter(ColourDatabase.toList(), this)
}