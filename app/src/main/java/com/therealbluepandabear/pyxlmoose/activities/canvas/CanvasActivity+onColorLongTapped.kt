package com.therealbluepandabear.pyxlmoose.activities.canvas

import com.therealbluepandabear.pyxlmoose.converters.JsonConverter
import com.therealbluepandabear.pyxlmoose.database.AppData
import com.therealbluepandabear.pyxlmoose.enums.SnackbarDuration
import com.therealbluepandabear.pyxlmoose.extensions.showSnackbarWithAction
import com.therealbluepandabear.pyxlmoose.models.ColorPalette

fun extendedOnColorLongTapped(colorPalette: ColorPalette, colorIndex: Int) {
    val copy2 = JsonConverter.convertJsonStringToListOfInt(colorPalette.colorPaletteColorData).toMutableList()

    val extractedJson =
        JsonConverter.convertJsonStringToListOfInt(colorPalette.colorPaletteColorData).toMutableList()
    extractedJson.removeAt(colorIndex)

    val color = copy2[colorIndex]

    AppData.colorPalettesDB.colorPalettesDao().updateColorPaletteColorData(
        JsonConverter.convertListOfIntToJsonString(extractedJson), colorPalette.objId)

    val colorPaletteName = colorPalette.colorPaletteName

    binding.activityCanvasRootLayout.showSnackbarWithAction("Removed color from '$colorPaletteName'", SnackbarDuration.Default, "Undo") {
        extractedJson.add(colorIndex, color)

        AppData.colorPalettesDB.colorPalettesDao().updateColorPaletteColorData(
            JsonConverter.convertListOfIntToJsonString(extractedJson), colorPalette.objId)
    }
}