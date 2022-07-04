package com.therealbluepandabear.pixapencil.activities.main

import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.database.ColorPalettesDatabase
import com.therealbluepandabear.pixapencil.database.PixelArtDatabase

fun MainActivity.initializeRoomDatabases() {
    AppData.colorPalettesDB = ColorPalettesDatabase.getDatabase(this)
}