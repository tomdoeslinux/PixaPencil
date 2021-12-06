package com.realtomjoney.pyxlmoose.activities.canvas

import androidx.recyclerview.widget.LinearLayoutManager
import com.realtomjoney.pyxlmoose.database.ColorDatabase
import com.realtomjoney.pyxlmoose.adapters.ColorPickerAdapter

fun CanvasActivity.extendedSetUpRecyclerView() {
    val layoutManager = LinearLayoutManager(this)
    layoutManager.orientation = LinearLayoutManager.HORIZONTAL
    binding.activityCanvasColorPickerRecyclerView.layoutManager = layoutManager
    binding.activityCanvasColorPickerRecyclerView.adapter = ColorPickerAdapter(ColorDatabase.toList(), this)
}