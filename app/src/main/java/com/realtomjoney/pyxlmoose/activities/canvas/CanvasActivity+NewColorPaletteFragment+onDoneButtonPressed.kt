package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.adapters.ColorPickerAdapter
import com.realtomjoney.pyxlmoose.converters.JsonConverter
import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.extensions.navigateHome
import com.realtomjoney.pyxlmoose.models.ColorPalette
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun CanvasActivity.extendedOnDoneButtonPressed(colorPaletteTitle: String) {
    currentFragmentInstance = null
    navigateHome(supportFragmentManager, newColorPaletteFragmentInstance, binding.activityCanvasRootLayout, binding.activityCanvasPrimaryFragmentHost, intent.getStringExtra("PROJECT_TITLE")!!)

    CoroutineScope(Dispatchers.IO).launch {
        AppData.colorPalettesDB.colorPalettesDao().insertColorPalette(ColorPalette(colorPaletteTitle, JsonConverter.convertListOfIntToJsonString(listOf())))
    }

    AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettes().observe(this, {
        binding.activityCanvasColorPickerRecyclerView.adapter = ColorPickerAdapter(it.last(), this)
    })
}