package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.activities.main.extendedOnResume
import com.realtomjoney.pyxlmoose.adapters.ColorPickerAdapter
import com.realtomjoney.pyxlmoose.adapters.RecentCreationsAdapter
import com.realtomjoney.pyxlmoose.converters.JsonConverter
import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.extensions.navigateHome
import com.realtomjoney.pyxlmoose.models.ColorPalette
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun CanvasActivity.extendedOnDoneButtonPressed(selectedColor: Int, colorPaletteMode: Boolean) {
    if (!colorPaletteMode) setPixelColor(selectedColor)
    else {
        val newData = JsonConverter.convertJsonStringToListOfInt(fromDB!!.colorPaletteColorData).toMutableList()
        newData.add(selectedColor)

        AppData.colorPalettesDB.colorPalettesDao().updateColorPaletteColorData(JsonConverter.convertListOfIntToJsonString(newData.toList()), fromDB!!.objId)
        AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettes().observe(this, {
            binding.activityCanvasColorPickerRecyclerView.adapter = ColorPickerAdapter(fromDB!!, this)
            binding.activityCanvasColorPickerRecyclerView.scrollToPosition(JsonConverter.convertJsonStringToListOfInt(fromDB!!.colorPaletteColorData).size)
        })
    }

    currentFragmentInstance = null
    navigateHome(supportFragmentManager, colorPickerFragmentInstance, binding.activityCanvasRootLayout, binding.activityCanvasPrimaryFragmentHost, intent.getStringExtra("PROJECT_TITLE")!!)
}

fun CanvasActivity.extendedOnDoneButtonPressed(colorToFind: Int?, colorToReplace: Int?) {
    canvasStates.add(canvasFragmentInstance.myCanvasViewInstance.saveData())

    currentFragmentInstance = null

    val dataAsPixelList = canvasFragmentInstance.myCanvasViewInstance.saveData()

    for (pixel in dataAsPixelList) {
        if (pixel.pixelColor != null && pixel.pixelColor == colorToFind && colorToReplace != null) pixel.pixelColor = colorToReplace
    }

    canvasFragmentInstance.myCanvasViewInstance.drawFromPixelList(dataAsPixelList)

    navigateHome(supportFragmentManager, findAndReplaceFragmentInstance, binding.activityCanvasRootLayout, binding.activityCanvasPrimaryFragmentHost, intent.getStringExtra("PROJECT_TITLE")!!)

    extendedSetUpRecyclerView()
}

fun CanvasActivity.extendedOnDoneButtonPressed(colorPaletteTitle: String) {
    currentFragmentInstance = null
    navigateHome(supportFragmentManager, newColorPaletteFragmentInstance, binding.activityCanvasRootLayout, binding.activityCanvasPrimaryFragmentHost, intent.getStringExtra("PROJECT_TITLE")!!)

    CoroutineScope(Dispatchers.IO).launch {
        AppData.colorPalettesDB.colorPalettesDao().insertColorPalette(ColorPalette(colorPaletteTitle, JsonConverter.convertListOfIntToJsonString(listOf())))
    }

    AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettes().observe(this, {
        binding.activityCanvasColorPickerRecyclerView.adapter = ColorPickerAdapter(it.last(), this)
    })
}