package com.therealbluepandabear.pixapencil.activities.main.oncreate

import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.database.ColorPalettesDatabase

fun MainActivity.initializeRoomDatabases() {
    AppData.colorPalettesDB = ColorPalettesDatabase.getDatabase(this)
}