package com.therealbluepandabear.pixapencil.activities.canvas.ondonebuttonpressed

import android.graphics.Color
import androidx.lifecycle.lifecycleScope
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.extendedOnColorPaletteTapped
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.ColorPalette
import com.therealbluepandabear.pixapencil.utility.LongConstants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun CanvasActivity.extendedOnDoneButtonPressed(colorPaletteTitle: String, extractColorPaletteFromCanvas: Boolean) {
    supportFragmentManager.popBackStackImmediate()

    CoroutineScope(Dispatchers.IO).launch {
        if (!extractColorPaletteFromCanvas) {
            AppData.colorPalettesDB.colorPalettesDao().insertColorPalette(
                ColorPalette(
                    colorPaletteTitle,
                    JsonConverter.convertListToJsonString(listOf(Color.TRANSPARENT))
                )
            )
        } else {
            val data = pixelGridViewInstance.getNumberOfUniqueColors().toMutableList()
            data.add(Color.TRANSPARENT)

            AppData.colorPalettesDB.colorPalettesDao().insertColorPalette(
                ColorPalette(
                    colorPaletteTitle,
                    JsonConverter.convertListToJsonString(data)
                )
            )
        }
    }

    AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettes().observe(this) {
        lifecycleScope.launch {
            delay(LongConstants.DefaultHandlerDelay)
            extendedOnColorPaletteTapped(it.last())
        }
    }
}