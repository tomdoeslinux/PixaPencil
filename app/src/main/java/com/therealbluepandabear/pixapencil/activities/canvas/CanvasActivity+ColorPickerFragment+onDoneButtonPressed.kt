package com.therealbluepandabear.pixapencil.activities.canvas

import android.graphics.Color
import android.os.Handler
import com.therealbluepandabear.pixapencil.adapters.ColorPickerAdapter
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.utility.LongConstants

fun CanvasActivity.extendedOnDoneButtonPressed(selectedColor: Int, colorPaletteMode: Boolean) {
    navigateBack(colorPickerFragmentInstance)

    if (!colorPaletteMode) {
        Handler().postDelayed({
            setPixelColor(selectedColor)
        }, LongConstants.DefaultHandlerDelay)
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