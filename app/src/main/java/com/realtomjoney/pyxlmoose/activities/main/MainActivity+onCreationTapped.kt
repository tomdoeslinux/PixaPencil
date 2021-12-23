package com.realtomjoney.pyxlmoose.activities.main

import android.content.Intent
import com.realtomjoney.pyxlmoose.activities.canvas.CanvasActivity
import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.models.PixelArt

fun MainActivity.extendedOnCreationTapped(param: PixelArt) {
    AppData.pixelArtDB.pixelArtCreationsDao().getAllPixelArtCreations().observe(this, {
        startActivity(Intent(context, CanvasActivity::class.java)
            .putExtra("INDEX", it.indexOf(param))
            .putExtra("PROJECT_TITLE", param.title))
    })
}