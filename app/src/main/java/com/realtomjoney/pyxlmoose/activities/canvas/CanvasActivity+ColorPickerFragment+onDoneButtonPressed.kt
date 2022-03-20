package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.Color
import android.os.Handler
import android.os.Looper
import com.realtomjoney.pyxlmoose.adapters.ColorPickerAdapter
import com.realtomjoney.pyxlmoose.converters.JsonConverter
import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.extensions.navigateHome
import com.realtomjoney.pyxlmoose.utility.LongConstants
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun CanvasActivity.extendedOnDoneButtonPressed(selectedColor: Int, colorPaletteMode: Boolean) {

    var size = 0
    if (!colorPaletteMode) {
        setPixelColor(selectedColor)
    } else {
        val newData = JsonConverter.convertJsonStringToListOfInt(fromDB!!.colorPaletteColorData).toMutableList()
        newData.add(selectedColor)
        newData.remove(Color.TRANSPARENT)
        newData.add(Color.TRANSPARENT)

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

    currentFragmentInstance = null
    navigateHome(supportFragmentManager, colorPickerFragmentInstance, binding.activityCanvasRootLayout, binding.activityCanvasPrimaryFragmentHost, intent.getStringExtra(StringConstants.ProjectTitleExtra)!!)
    switchSelectedColorIndicator()
    showMenuItems()

    val h = Handler(Looper.getMainLooper())
    h.postDelayed( {
        binding.activityCanvasColorPickerRecyclerView.scrollToPosition(size - 1)
    }, LongConstants.DEF_HANDLER_DELAY)
}