package com.therealbluepandabear.pixapencil.activities.canvas.ontapped

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.binding
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.extensions.showSnackbarWithAction
import com.therealbluepandabear.pixapencil.models.ColorPalette
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun CanvasActivity.extendedOnColorLongTapped(colorPalette: ColorPalette, colorIndex: Int) {
    val extractedJson = JsonConverter.convertJsonStringToListOfInt(colorPalette.colorPaletteColorData).toMutableList()
    val colorRemoved = extractedJson[colorIndex]
    extractedJson.removeAt(colorIndex)

    colorPalette.colorPaletteColorData = JsonConverter.convertListToJsonString(extractedJson)

    CoroutineScope(Dispatchers.IO).launch {
        AppData.colorPalettesDB.colorPalettesDao().updateColorPalette(colorPalette)
    }

    binding.root.showSnackbarWithAction(getString(R.string.snackbar_on_color_long_tapped_in_code_str, colorPalette.colorPaletteName), SnackbarDuration.Default, getString(R.string.activityCanvasTopAppMenu_undo_str)) {
        extractedJson.add(colorIndex, colorRemoved)
        colorPalette.colorPaletteColorData = JsonConverter.convertListToJsonString(extractedJson)

        CoroutineScope(Dispatchers.IO).launch {
            AppData.colorPalettesDB.colorPalettesDao().updateColorPalette(colorPalette)
        }
    }
}