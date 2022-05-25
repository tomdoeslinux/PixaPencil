package com.therealbluepandabear.pixapencil.activities.canvas.ondonebuttonpressed

import android.graphics.Color
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.binding
import com.therealbluepandabear.pixapencil.activities.canvas.judgeUndoRedoStacks
import com.therealbluepandabear.pixapencil.activities.canvas.ontapped.extendedOnColorPaletteTapped
import com.therealbluepandabear.pixapencil.activities.canvas.setPixelColor
import com.therealbluepandabear.pixapencil.adapters.ColorPaletteColorPickerAdapter
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.models.ColorPalette
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun CanvasActivity.extendedOnDoneButtonPressed(selectedColor: Int, colorPalette: ColorPalette? = null) {
    supportFragmentManager.popBackStackImmediate()

    if (colorPalette == null) {
        setPixelColor(selectedColor)
    } else {
        val newData = JsonConverter.convertJsonStringToListOfInt(colorPalette.colorPaletteColorData).toMutableList()
        newData.add(newData.size - 1, selectedColor)

        colorPalette.colorPaletteColorData = JsonConverter.convertListToJsonString(newData.toList())


        CoroutineScope(Dispatchers.IO).launch {
            AppData.colorPalettesDB.colorPalettesDao().updateColorPalette(colorPalette)
        }

        adapter = ColorPaletteColorPickerAdapter(colorPalette, this@extendedOnDoneButtonPressed)
        binding.activityCanvasColorPickerRecyclerView.adapter = adapter

        val colorData = JsonConverter.convertJsonStringToListOfInt(colorPalette.colorPaletteColorData).toMutableList()
        binding.activityCanvasColorPickerRecyclerView.scrollToPosition(colorData.indexOf(Color.TRANSPARENT))
    }

    binding.root.post {
        judgeUndoRedoStacks()
    }
}