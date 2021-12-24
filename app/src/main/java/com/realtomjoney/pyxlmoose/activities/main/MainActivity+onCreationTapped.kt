package com.realtomjoney.pyxlmoose.activities.main

import android.content.Intent
import com.realtomjoney.pyxlmoose.activities.canvas.CanvasActivity
import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.models.PixelArt
import com.realtomjoney.pyxlmoose.utility.StringConstants

fun MainActivity.extendedOnCreationTapped(param: PixelArt) {
    AppData.pixelArtDB.pixelArtCreationsDao().getAllPixelArtCreations().observe(this, {
        startActivity(Intent(context, CanvasActivity::class.java)
            .putExtra(StringConstants.INDEX_EXTRA, it.indexOf(param))
            .putExtra(StringConstants.PROJECT_TITLE_EXTRA, param.title))
    })
}