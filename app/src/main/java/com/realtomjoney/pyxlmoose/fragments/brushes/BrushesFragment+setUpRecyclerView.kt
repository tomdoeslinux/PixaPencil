package com.realtomjoney.pyxlmoose.fragments.brushes

import android.content.res.Configuration
import androidx.recyclerview.widget.LinearLayoutManager
import com.realtomjoney.pyxlmoose.adapters.BrushesAdapter
import com.realtomjoney.pyxlmoose.database.BrushesDatabase
import com.realtomjoney.pyxlmoose.extensions.getScreenOrientation

fun BrushesFragment.setUpRecyclerView() {
    val layoutManager = LinearLayoutManager(activity)

    val layoutManagerOrientation: Int = if (activity?.getScreenOrientation() == Configuration.ORIENTATION_PORTRAIT) {
        LinearLayoutManager.HORIZONTAL
    } else {
        LinearLayoutManager.VERTICAL
    }

    layoutManager.orientation = layoutManagerOrientation

    binding.fragmentBrushesRecyclerView.layoutManager = layoutManager

    binding.fragmentBrushesRecyclerView.adapter = BrushesAdapter(BrushesDatabase.toList(), this)
}