package com.therealbluepandabear.pixapencil.activities.canvas

import android.graphics.Color
import android.os.Handler
import androidx.lifecycle.lifecycleScope
import com.therealbluepandabear.pixapencil.adapters.ColorPickerAdapter
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.utility.LongConstants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun CanvasActivity.extendedOnDoneButtonPressed(selectedColor: Int, colorPaletteMode: Boolean) {
    navigateBack(colorPickerFragmentInstance)

    if (!colorPaletteMode) {
        lifecycleScope.launch {
            delay(LongConstants.DefaultHandlerDelay)
            setPixelColor(selectedColor)
        }
    } else {
        val newData = JsonConverter.convertJsonStringToListOfInt(fromDB!!.colorPaletteColorData).toMutableList()
        newData.add(selectedColor)

        newData.distinctBy { it == Color.TRANSPARENT }
        newData.sortBy { it == Color.TRANSPARENT }

        AppData.colorPalettesDB.colorPalettesDao().updateColorPaletteColorData(JsonConverter.convertListToJsonString(newData.toList()), fromDB!!.objId)
        AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettes().observe(this) {
            binding.activityCanvasColorPickerRecyclerView.adapter =
                ColorPickerAdapter(fromDB!!, this)
        }
    }
}