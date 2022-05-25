package com.therealbluepandabear.pixapencil.extensions

import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.models.ColorPalette

fun ColorPalette.getFindAndReplaceCompatibleColorPaletteColorData(): MutableList<Int> {
    val palette = JsonConverter.convertJsonStringToListOfInt(colorPaletteColorData).toMutableList()
    palette.removeLast()

    return palette
}