package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.database.ColorPalettesDatabase

fun CanvasActivity.onCreate() {
    if (!AppData.colorPalettesDBInitialized) {
        AppData.colorPalettesDB = ColorPalettesDatabase.getDatabase(this)
    }
    getExtras()
    setUpFragment()
    setBindings()
    setUpRecyclerView()
    setOnClickListeners()
    setColors()
    initSharedPreferenceObject()
    setObjectGlobalScopeLifecycleOwner()
    setFlags()
}