package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.converters.BitmapConverter
import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.database.ColorPalettesDatabase
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun CanvasActivity.onCreate() {
    getExtras()
    AppData.colorPalettesDB = ColorPalettesDatabase.getDatabase(this)

    setUpFragment()
    setBindings()
    setUpRecyclerView()
    setOnClickListeners()
    setColors()
    initSharedPreferenceObject()

    if (index != -1) {
        AppData.pixelArtDB.pixelArtCreationsDao().getAllPixelArtCreations().observe(context) {
            currentPixelArtObj = it[index!!]
            outerCanvasInstance.canvasFragment.myCanvasViewInstance.replaceBitmap(BitmapConverter.convertStringToBitmap(currentPixelArtObj.bitmap)!!)
        }
    }
}