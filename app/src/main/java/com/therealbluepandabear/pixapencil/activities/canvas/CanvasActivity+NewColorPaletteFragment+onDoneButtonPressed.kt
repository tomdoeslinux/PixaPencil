package com.therealbluepandabear.pixapencil.activities.canvas

import android.graphics.Color
import android.os.Handler
import android.os.Looper
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.ColorPalette
import com.therealbluepandabear.pixapencil.utility.LongConstants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun CanvasActivity.extendedOnDoneButtonPressed(colorPaletteTitle: String, extractColorPaletteFromCanvas: Boolean) {
    navigateBack(newColorPaletteFragmentInstance)

    CoroutineScope(Dispatchers.IO).launch {
        if (!extractColorPaletteFromCanvas) {
            AppData.colorPalettesDB.colorPalettesDao().insertColorPalette(
                ColorPalette(
                    colorPaletteTitle,
                    JsonConverter.convertListOfIntToJsonString(listOf(Color.TRANSPARENT))
                )
            )
        } else {
            val data = pixelGridViewInstance.getNumberOfUniqueColors().toMutableList()
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
        val h = Handler(Looper.getMainLooper())
        h.postDelayed( {
            extendedOnColorPaletteTapped(it.last())
        }, LongConstants.DefaultHandlerDelay)
    }
}