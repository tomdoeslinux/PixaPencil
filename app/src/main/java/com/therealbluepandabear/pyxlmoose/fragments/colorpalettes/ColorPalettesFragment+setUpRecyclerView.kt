package com.therealbluepandabear.pyxlmoose.fragments.colorpalettes

import android.app.Activity
import android.content.res.Configuration
import androidx.recyclerview.widget.LinearLayoutManager
import com.therealbluepandabear.pyxlmoose.adapters.ColorPalettesAdapter
import com.therealbluepandabear.pyxlmoose.database.AppData
import com.therealbluepandabear.pyxlmoose.extensions.getScreenOrientation
import com.therealbluepandabear.pyxlmoose.utility.ObjectConstants

fun ColorPalettesFragment.setUpRecyclerView() {
    val activity = this.activity

    binding.apply {
        val layoutManager = LinearLayoutManager(activity)

        val layoutManagerOrientation: Int = if (activity?.getScreenOrientation() == Configuration.ORIENTATION_PORTRAIT) {
            LinearLayoutManager.HORIZONTAL
        } else {
            LinearLayoutManager.VERTICAL
        }

        layoutManager.orientation = layoutManagerOrientation

        AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettes().observe(ObjectConstants.ObjectGlobalScopeLifecycleOwner) {
            fragmentColorPalettesRecyclerView.adapter = ColorPalettesAdapter(activity as Activity, it, context)
        }
    }
}