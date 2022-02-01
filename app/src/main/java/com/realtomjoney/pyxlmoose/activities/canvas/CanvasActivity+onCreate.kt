package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.database.ColorPalettesDatabase

fun CanvasActivity.onCreate() {
    getExtras()
    AppData.colorPalettesDB = ColorPalettesDatabase.getDatabase(this)

    setUpFragment()
    setBindings()
    setUpRecyclerView()
    setOnClickListeners()
    setColors()
    initSharedPreferenceObject()
    replaceBitmapIfApplicable()
}