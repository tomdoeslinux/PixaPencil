package com.therealbluepandabear.pixapencil.fragments.colorpalettes

import com.therealbluepandabear.pixapencil.database.AppData

fun ColorPalettesFragment.observeColorPaletteData() {
    AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettes().observe(this) {
        colorPalettesList.clear()
        colorPalettesList.addAll(it)

        adapter.updateDataSource(colorPalettesList)
    }
}