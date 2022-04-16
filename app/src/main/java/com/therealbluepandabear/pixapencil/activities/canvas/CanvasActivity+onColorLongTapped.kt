package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.extensions.showSnackbarWithAction
import com.therealbluepandabear.pixapencil.models.ColorPalette

fun extendedOnColorLongTapped(colorPalette: ColorPalette, colorIndex: Int) {
    val copy2 = JsonConverter.convertJsonStringToListOfInt(colorPalette.colorPaletteColorData).toMutableList()

    val extractedJson =
        JsonConverter.convertJsonStringToListOfInt(colorPalette.colorPaletteColorData).toMutableList()
    extractedJson.removeAt(colorIndex)

    val color = copy2[colorIndex]

    AppData.colorPalettesDB.colorPalettesDao().updateColorPaletteColorData(
        JsonConverter.convertListToJsonString(extractedJson), colorPalette.objId)

    val colorPaletteName = colorPalette.colorPaletteName

    binding.activityCanvasRootLayout.showSnackbarWithAction("Removed color from '$colorPaletteName'", SnackbarDuration.Default, "Undo") {
        extractedJson.add(colorIndex, color)

        AppData.colorPalettesDB.colorPalettesDao().updateColorPaletteColorData(
            JsonConverter.convertListToJsonString(extractedJson), colorPalette.objId)
    }
}