package com.therealbluepandabear.pixapencil.utility

import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.models.ColorPalette

object ColorPaletteUtilities {
    fun getFindAndReplaceCompatibleColorPaletteColorData(colorPalette: ColorPalette): MutableList<Int> {
        val palette = JsonConverter.convertJsonStringToListOfInt(colorPalette.colorPaletteColorData).toMutableList()
        palette.removeLast()

        return palette
    }
}