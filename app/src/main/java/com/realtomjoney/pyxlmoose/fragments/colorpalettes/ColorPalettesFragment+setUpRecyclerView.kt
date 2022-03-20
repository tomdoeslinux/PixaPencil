package com.realtomjoney.pyxlmoose.fragments.colorpalettes

import android.app.Activity
import android.content.res.Configuration
import androidx.recyclerview.widget.LinearLayoutManager
import com.realtomjoney.pyxlmoose.adapters.ColorPalettesAdapter
import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.extensions.getScreenOrientation
import com.realtomjoney.pyxlmoose.utility.ObjectConstants

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

        AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettes().observe(ObjectConstants.OBJECT_GLOBAL_SCOPE_LIFECYCLE_OWNER) {
            fragmentColorPalettesRecyclerView.adapter = ColorPalettesAdapter(activity as Activity, it, context)
        }
    }
}