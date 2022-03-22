package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.Color
import android.os.Handler
import android.os.Looper
import com.realtomjoney.pyxlmoose.converters.JsonConverter
import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.models.ColorPalette
import com.realtomjoney.pyxlmoose.utility.LongConstants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun CanvasActivity.extendedOnDoneButtonPressed(colorPaletteTitle: String, extractColorPaletteFromCanvas: Boolean) {
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

    navigateBack(colorPickerFragmentInstance)


    AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettes().observe(this) {
        val h = Handler(Looper.getMainLooper())
        h.postDelayed( {
            extendedOnColorPaletteTapped(it.last())
        }, LongConstants.DefaultHandlerDelay)
    }
}