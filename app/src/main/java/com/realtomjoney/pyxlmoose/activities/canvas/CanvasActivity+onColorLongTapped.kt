package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.converters.JsonConverter
import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.extensions.SnackbarDuration
import com.realtomjoney.pyxlmoose.extensions.showSnackbarWithAction
import com.realtomjoney.pyxlmoose.models.ColorPalette

fun extendedOnColorLongTapped(colorPalette: ColorPalette, colorIndex: Int) {
    val copy2 = JsonConverter.convertJsonStringToListOfInt(colorPalette.colorPaletteColorData).toMutableList()

    val extractedJson =
        JsonConverter.convertJsonStringToListOfInt(colorPalette.colorPaletteColorData).toMutableList()
    extractedJson.removeAt(colorIndex)

    val color = copy2[colorIndex]

    AppData.colorPalettesDB.colorPalettesDao().updateColorPaletteColorData(
        JsonConverter.convertListOfIntToJsonString(extractedJson), colorPalette.objId)

    val colorPaletteName = colorPalette.colorPaletteName

    binding.activityCanvasRootLayout.showSnackbarWithAction("Removed color from '$colorPaletteName'", SnackbarDuration.DEFAULT, "Undo") {
        extractedJson.add(colorIndex, color)

        AppData.colorPalettesDB.colorPalettesDao().updateColorPaletteColorData(
            JsonConverter.convertListOfIntToJsonString(extractedJson), colorPalette.objId)
    }
}