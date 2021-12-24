package com.realtomjoney.pyxlmoose.activities.main

import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.database.ColorPalettesDatabase
import com.realtomjoney.pyxlmoose.database.PixelArtDatabase

fun MainActivity.initializeRoomDatabases() {
    AppData.pixelArtDB = PixelArtDatabase.getDatabase(this)
    AppData.colorPalettesDB = ColorPalettesDatabase.getDatabase(this)
}