package com.therealbluepandabear.pixapencil.fragments.colorpalettes

import android.content.res.Configuration
import androidx.recyclerview.widget.LinearLayoutManager
import com.therealbluepandabear.pixapencil.adapters.ColorPalettesAdapter

fun ColorPalettesFragment.setUpRecyclerView() {
    val activity = this.activity

    val layoutManager = LinearLayoutManager(activity)

    val layoutManagerOrientation: Int = if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
        LinearLayoutManager.HORIZONTAL
    } else {
        LinearLayoutManager.VERTICAL
    }

    layoutManager.orientation = layoutManagerOrientation

    adapter = ColorPalettesAdapter(mutableListOf(), context, this@setUpRecyclerView.requireContext())
    binding.fragmentColorPalettesRecyclerView.adapter = adapter
}