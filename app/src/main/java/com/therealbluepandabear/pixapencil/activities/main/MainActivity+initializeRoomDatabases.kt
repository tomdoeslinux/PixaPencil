package com.therealbluepandabear.pixapencil.activities.main

import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.database.ColorPalettesDatabase
import com.therealbluepandabear.pixapencil.database.PixelArtDatabase

fun MainActivity.initializeRoomDatabases() {
    AppData.pixelArtDB = PixelArtDatabase.getDatabase(this)
    AppData.colorPalettesDB = ColorPalettesDatabase.getDatabase(this)
}