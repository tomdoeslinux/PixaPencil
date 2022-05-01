package com.therealbluepandabear.pixapencil.fragments.colorpalettes

import android.content.res.Configuration
import androidx.recyclerview.widget.LinearLayoutManager
import com.therealbluepandabear.pixapencil.adapters.ColorPalettesAdapter
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.utility.ObjectConstants

fun ColorPalettesFragment.setUpRecyclerView() {
    val activity = this.activity

    binding.apply {
        val layoutManager = LinearLayoutManager(activity)

        val layoutManagerOrientation: Int = if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            LinearLayoutManager.HORIZONTAL
        } else {
            LinearLayoutManager.VERTICAL
        }

        layoutManager.orientation = layoutManagerOrientation

        AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettes().observe(ObjectConstants.ObjectGlobalScopeLifecycleOwner) {
            fragmentColorPalettesRecyclerView.adapter = ColorPalettesAdapter(it, context)
        }
    }
}