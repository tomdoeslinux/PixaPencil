package com.therealbluepandabear.pixapencil.activities.canvas

import android.graphics.Color
import android.os.Handler
import android.os.Looper
import com.therealbluepandabear.pixapencil.adapters.ColorPickerAdapter
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.utility.LongConstants

fun CanvasActivity.extendedOnDoneButtonPressed(selectedColor: Int, colorPaletteMode: Boolean) {
    var size = 0
    if (!colorPaletteMode) {
        setPixelColor(selectedColor)
    } else {
        val newData = JsonConverter.convertJsonStringToListOfInt(fromDB!!.colorPaletteColorData).toMutableList()
        newData.add(selectedColor)

        newData.distinctBy { it == Color.TRANSPARENT }
        newData.sortBy { it == Color.TRANSPARENT }

        AppData.colorPalettesDB.colorPalettesDao().updateColorPaletteColorData(JsonConverter.convertListOfIntToJsonString(newData.toList()), fromDB!!.objId)
        AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettes().observe(this) {
            binding.activityCanvasColorPickerRecyclerView.adapter =
                ColorPickerAdapter(fromDB!!, this)
            size =
                JsonConverter.convertJsonStringToListOfInt(
                    fromDB!!.colorPaletteColorData
                ).size
        }
    }

    navigateBack(colorPickerFragmentInstance)

    Handler(Looper.getMainLooper()).postDelayed( {
        binding.activityCanvasColorPickerRecyclerView.scrollToPosition(size)
    }, LongConstants.DefaultHandlerDelay)
}