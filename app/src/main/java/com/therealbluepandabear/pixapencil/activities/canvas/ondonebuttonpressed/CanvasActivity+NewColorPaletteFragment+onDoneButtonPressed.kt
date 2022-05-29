package com.therealbluepandabear.pixapencil.activities.canvas.ondonebuttonpressed

import android.graphics.Color
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.binding
import com.therealbluepandabear.pixapencil.activities.canvas.judgeUndoRedoStacks
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.ColorPalette
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

lateinit var palette: ColorPalette

fun CanvasActivity.extendedOnDoneButtonPressed(colorPaletteTitle: String, extractColorPaletteFromCanvas: Boolean) {
    supportFragmentManager.popBackStackImmediate()

    CoroutineScope(Dispatchers.IO).launch {
        if (!extractColorPaletteFromCanvas) {
            palette = ColorPalette(colorPaletteTitle, JsonConverter.convertListToJsonString(listOf(Color.TRANSPARENT)))
            AppData.colorPalettesDB.colorPalettesDao().insertColorPalette(palette)
        } else {
            val data = pixelGridViewInstance.getNumberOfUniqueColors().toMutableList()
            data.add(Color.TRANSPARENT)

            palette = ColorPalette(colorPaletteTitle, JsonConverter.convertListToJsonString(data))
            AppData.colorPalettesDB.colorPalettesDao().insertColorPalette(palette)
        }
    }

    binding.root.post {
        judgeUndoRedoStacks()
        onColorPaletteTapped(palette)
    }
}