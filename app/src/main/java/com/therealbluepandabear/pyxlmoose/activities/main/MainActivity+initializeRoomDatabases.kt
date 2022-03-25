package com.therealbluepandabear.pyxlmoose.activities.main

import com.therealbluepandabear.pyxlmoose.database.AppData
import com.therealbluepandabear.pyxlmoose.database.ColorPalettesDatabase
import com.therealbluepandabear.pyxlmoose.database.PixelArtDatabase

fun MainActivity.initializeRoomDatabases() {
    AppData.pixelArtDB = PixelArtDatabase.getDatabase(this)
    AppData.colorPalettesDB = ColorPalettesDatabase.getDatabase(this)
}