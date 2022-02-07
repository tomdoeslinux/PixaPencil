package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.Color
import android.util.Log
import com.realtomjoney.pyxlmoose.adapters.ColorPickerAdapter
import com.realtomjoney.pyxlmoose.converters.JsonConverter
import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.extensions.navigateHome
import com.realtomjoney.pyxlmoose.models.ColorPalette
import com.realtomjoney.pyxlmoose.utility.StringConstants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun CanvasActivity.extendedOnDoneButtonPressed(colorPaletteTitle: String, extractColorPaletteFromCanvas: Boolean) {
    showMenuItems()
    currentFragmentInstance = null
    navigateHome(supportFragmentManager, newColorPaletteFragmentInstance, binding.activityCanvasRootLayout, binding.activityCanvasPrimaryFragmentHost, StringConstants.PROJECT_TITLE_EXTRA)

    CoroutineScope(Dispatchers.IO).launch {
        if (!extractColorPaletteFromCanvas) {
            AppData.colorPalettesDB.colorPalettesDao().insertColorPalette(
                ColorPalette(
                    colorPaletteTitle,
                    JsonConverter.convertListOfIntToJsonString(listOf(Color.TRANSPARENT))
                )
            )
        } else {
            val data = outerCanvasInstance.canvasFragment.myCanvasViewInstance.getNumberOfUniqueColors().toMutableList()
            data.add(Color.TRANSPARENT)

            AppData.colorPalettesDB.colorPalettesDao().insertColorPalette(
                ColorPalette(
                    colorPaletteTitle,
                    JsonConverter.convertListOfIntToJsonString(data)
                )
            )
        }
    }

    AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettes().observe(this) {
        binding.activityCanvasColorPickerRecyclerView.adapter = ColorPickerAdapter(it.last(), this)
    }
}